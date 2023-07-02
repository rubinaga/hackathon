package com.example.EcoHack.app.AppUser;

import com.example.EcoHack.app.AppUser.DTO.CreateAppUserDTO;
import com.example.EcoHack.app.AppUser.DTO.GetAppUserDTO;
import com.example.EcoHack.app.AppUser.DTO.UpdateAppUserDTO;
import com.example.EcoHack.app.AppUser.Role.RoleService;
import com.example.EcoHack.exceptions.api.ResourceNotFoundException;
import com.example.EcoHack.common.responseFactory.ResponseFactory;
import com.example.EcoHack.security.AuthenticationFacade;
import com.example.EcoHack.security.DTO.DetailedTokenDetailsDTO;
import com.example.EcoHack.security.JwtUtils;
import com.example.EcoHack.util.localFileStore.LocalImage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;
    private final RoleService roleService;
    private final JwtUtils jwtUtils;
    @Autowired
    ApplicationEventPublisher eventPublisher;


    @PostMapping("public/users/register")
    public ResponseEntity<DetailedTokenDetailsDTO> registerUser(@RequestBody @Valid CreateAppUserDTO createAppUserDTO, HttpServletRequest request) {
        if (createAppUserDTO.getLocale() == null)
            createAppUserDTO.setLocale(request.getLocale());
        AppUser appUser = appUserService.saveUser(new AppUser(createAppUserDTO));
        return ResponseEntity.ok().body(jwtUtils.generateDetailedTokenPair(appUser.toDomainUser()));
    }


    @GetMapping("admin/users")
    public ResponseEntity<List<GetAppUserDTO>> getUsers() {
        return ResponseEntity.ok().body(appUserService.getUsers().stream().map(u -> u.toGetAppUserDTO()).collect(Collectors.toList()));
    }

    @DeleteMapping("admin/users/{ids}")
    public ResponseEntity<Object> deleteUsers(@PathVariable List<Long> ids) {
        List<AbstractMap.SimpleEntry<Long, String>> response = new ArrayList<>();
        for (Long id : ids) {
            AbstractMap.SimpleEntry<Long, String> pair;
            if (appUserService.existsById(id)) {
                appUserService.delete(id);
                response.add(new AbstractMap.SimpleEntry<>(id, "Success!"));
            } else
                response.add(new AbstractMap.SimpleEntry<>(id, new ResourceNotFoundException("User", "Id", id.toString()).getMessage()));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("user/users/{username}")
    public ResponseEntity<GetAppUserDTO> getUserByUsername(@PathVariable String username) {
        if (!AuthenticationFacade.isAdminOrCurrentUserOnCurrentSecurityContext(username))
            return ResponseEntity.status(403).body(null);
        AppUser user = appUserService.getByUsername(username);
        return new ResponseEntity<GetAppUserDTO>(user.toGetAppUserDTO(), HttpStatus.OK);
    }

    @PatchMapping("user/users/{username}")
    public ResponseEntity<GetAppUserDTO> updateUserByUsername(@PathVariable String username, @RequestBody UpdateAppUserDTO updateAppUserDTO) {
        if (!AuthenticationFacade.isAdminOrCurrentUserOnCurrentSecurityContext(username))
            return ResponseEntity.status(403).body(null);
        return new ResponseEntity<GetAppUserDTO>(appUserService.updateUser(username, updateAppUserDTO).toGetAppUserDTO(), HttpStatus.OK);
    }

    @DeleteMapping("user/users/{username}")
    public ResponseEntity<Object> deleteUserByUsername(@PathVariable String username) {
        if (!AuthenticationFacade.isAdminOrCurrentUserOnCurrentSecurityContext(username))
            return ResponseEntity.status(403).body(null);
        appUserService.delete(username);
        return ResponseFactory.buildResourceDeletedSuccessfullyResponse();
    }

    @PostMapping("user/users/{username}/uploadImage")
    public ResponseEntity<Object> uploadImage(@RequestParam(name = "file", required = true) MultipartFile file, @PathVariable(name = "username", required = true) String username) throws IOException {
        if (!AuthenticationFacade.isAdminOrCurrentUserOnCurrentSecurityContext(username))
            return ResponseEntity.status(403).body(null);
        appUserService.uploadImage(file, username);
        return ResponseFactory.buildResourceCreatedSuccessfullyResponse();
    }
}

