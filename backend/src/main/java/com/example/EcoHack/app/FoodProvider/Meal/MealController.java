package com.example.EcoHack.app.FoodProvider.Meal;

import com.example.EcoHack.app.FoodProvider.Meal.DTO.GetModerateMealDTO;
import com.example.EcoHack.common.responseFactory.PageResponse;
import com.example.EcoHack.common.responseFactory.ResponseFactory;
import com.example.EcoHack.config.configProperties.DatabaseConfig;
import com.example.EcoHack.security.AuthenticationFacade;
import com.example.EcoHack.util.localFileStore.LocalImage;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
public class MealController {
    private final MealService mealService;

    public MealController(MealService mealService){
        this.mealService = mealService;
    }


    @GetMapping("public/meal")
    public ResponseEntity<PageResponse<GetModerateMealDTO>> getAllMeals(@RequestParam(name = "pageSize", defaultValue = "30") int pageSize, @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) { // todo create decorator to set available filter ops
        Page<GetModerateMealDTO> resultsPage = mealService.getAll(pageNumber, pageSize);
        return ResponseFactory.buildPageResponse(resultsPage);
    }


    @PostMapping("user/{username}/meals/uploadImage/{mealId}")
    public ResponseEntity<Object> uploadImage(@RequestParam(name = "file", required = true) MultipartFile file, @PathVariable(name = "username", required = true) String username, @PathVariable(name = "mealId", required = true) Long mealId) throws IOException {
        if (!AuthenticationFacade.isAdminOrCurrentUserOnCurrentSecurityContext(username))
            return ResponseEntity.status(403).body(null);
        mealService.uploadImage(file, mealId);
        return ResponseFactory.buildResourceCreatedSuccessfullyResponse();
    }
}
