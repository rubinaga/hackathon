package com.example.EcoHack.app.AppUser;

import com.example.EcoHack.app.AppUser.Role.RoleService;
import com.example.EcoHack.config.configProperties.DatabaseConfig;
import com.example.EcoHack.config.fileStore.AppUserImageStore;
import com.example.EcoHack.app.AppUser.DTO.UpdateAppUserDTO;
import com.example.EcoHack.exceptions.api.ResourceAlreadyExistsException;
import com.example.EcoHack.exceptions.api.ResourceNotFoundException;
import com.example.EcoHack.app.AppUser.Role.Role;
import com.example.EcoHack.common.BaseService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class AppUserService extends BaseService<AppUser> implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AppUserImageStore appUserImageStore;

    public AppUserService(AppUserRepository appUserRepository, RoleService roleService, PasswordEncoder passwordEncoder, AppUserImageStore appUserImageStore) {
        super(appUserRepository, "User");
        this.appUserRepository = appUserRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.appUserImageStore = appUserImageStore;
    }

    public boolean existsByUsername(String username) {
        return appUserRepository.existsByUsername(username);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = getByUsername(username);
        Collection<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(Optional.ofNullable(user.getRole()).map(Role::getName).orElse((DatabaseConfig.getUSER_ROLE().getName()))));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    // this method is used to register users internally, with no need to verify
    public AppUser getOrRegisterUserInternally(AppUser user) {
        AppUser appUser = appUserRepository.getByUsername(user.getUsername());
        if (appUser != null)
            return appUser;
        user.setRole(null);
        if (user.isPasswordUpdated())
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        return save(user);
    }

    public AppUser saveUser(AppUser user) {
        if (appUserRepository.existsByUsername(user.getUsername()))
            throw new ResourceAlreadyExistsException("User", "username", user.getUsername());
        if (user.isPasswordUpdated())
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        return (appUserRepository.save(user));
    }

    public AppUser setRoleToUser(String username, String roleName) {
        AppUser user = appUserRepository.findByUsername(username).orElseThrow(() -> {
            throw new ResourceNotFoundException("User", "username", username);
        });
        user.setRole(roleService.getByName(roleName));
        return save(user);
    }


    // TODO [1] refactor not found inside this function
    public Optional<AppUser> getUser(String username) {
        return appUserRepository.findByUsername(username);
    }
//    public Optional<AppUser> getUser(String username, AuthType au) {
//        return appUserRepository.findByUsername(username);
//    }

    @Override
    public void delete(Long id, boolean throwNotFoundEx) {
        if (existsById(id, throwNotFoundEx)) {
            AppUser appUser = getById(id);
            appUserRepository.deleteById(id);
        }
    }

    public void delete(String username) {
        AppUser appUser = getByUsername(username);
        appUserRepository.delete(appUser);
    }

    public void deleteAllUsersById(List<Long> ids) {
        appUserRepository.deleteAllById(ids);
    }

    public List<AppUser> getUsers() {
        log.info("Fetching all users");
        return appUserRepository.findAll();
    }

    public boolean matchesPassword(AppUser user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    public AppUser updateUser(String username, UpdateAppUserDTO updateAppUserDTO) {
        AppUser user = getByUsername(username);
        if (updateAppUserDTO.getSurname() != null)
            user.setSurname(updateAppUserDTO.getSurname());
        if (updateAppUserDTO.getPhoneNumber() != null)
            user.setPhoneNumber(updateAppUserDTO.getPhoneNumber());
        if (updateAppUserDTO.getName() != null)
            user.setName(updateAppUserDTO.getName());
        return save(user);
    }

    public AppUser getByUsername(String username) {
        return appUserRepository.findByUsername(username).orElseThrow(() -> buildResourceNotFoundException("username", username));
    }

    public AppUser uploadImage(MultipartFile file, String username) throws IOException {
        AppUser appUser = getByUsername(username);
        appUser.setImage(file.getBytes());
        return save(appUser);
    }
}
