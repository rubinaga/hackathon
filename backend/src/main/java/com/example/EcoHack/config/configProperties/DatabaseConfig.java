package com.example.EcoHack.config.configProperties;

import com.example.EcoHack.app.AppUser.AppUser;
import com.example.EcoHack.app.AppUser.AppUserService;
import com.example.EcoHack.app.AppUser.Role.Role;
import com.example.EcoHack.app.FoodProvider.Meal.Meal;
import com.example.EcoHack.app.FoodProvider.Meal.MealService;
import com.example.EcoHack.app.Ingredient.Ingredient;
import com.example.EcoHack.app.Ingredient.IngredientService;
import com.example.EcoHack.security.AuthenticationFacade;
import com.example.EcoHack.app.AppUser.Role.RoleService;
import lombok.Getter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

@Configuration
public class DatabaseConfig {

    private final RoleService roleService;
    private final AppUserService appUserService;

    private final IngredientService ingredientService;
    private final MealService mealService;

    public DatabaseConfig(RoleService roleService, AppUserService appUserService, IngredientService ingredientService, MealService mealService) {
        this.roleService = roleService;
        this.appUserService = appUserService;
        this.ingredientService = ingredientService;
        this.mealService = mealService;
    }

    @Getter
    private static Role FOOD_PROVIDER_ROLE = new Role("ROLE_FOOD_PROVIDER");
    @Getter
    private static Role USER_ROLE = new Role(AuthenticationFacade.USER_AUTHORITY.getAuthority());
    @Getter
    private static Role ADMIN_ROLE = new Role(AuthenticationFacade.ADMIN_AUTHORITY.getAuthority());
    @Getter
    private static AppUser ADMIN_USER = new AppUser("admin", "admin", "admin@gmail.com", "admin", "admin@gmail.com", "", false, null);
    private static AppUser FOOD_PROVIDER_USER = new AppUser("admin", "admin", "food@gmail.com", "admin", "food@gmail.com", "", true, null);

    @Bean
    CommandLineRunner rolesConfig() {
        return args -> {
            if (roleService.existsByName(FOOD_PROVIDER_ROLE.getName()))
                FOOD_PROVIDER_ROLE = roleService.getByName(FOOD_PROVIDER_ROLE.getName());
            else
                FOOD_PROVIDER_ROLE = roleService.save(FOOD_PROVIDER_ROLE);

            if (roleService.existsByName(USER_ROLE.getName()))
                USER_ROLE = roleService.getByName(USER_ROLE.getName());
            else
                USER_ROLE = roleService.save(USER_ROLE);

            if (roleService.existsByName(ADMIN_ROLE.getName()))
                ADMIN_ROLE = roleService.getByName(ADMIN_ROLE.getName());
            else
                ADMIN_ROLE = roleService.save(ADMIN_ROLE);

            if (roleService.existsByName(ADMIN_ROLE.getName()))
                ADMIN_ROLE = roleService.getByName(ADMIN_ROLE.getName());
            else
                ADMIN_ROLE = roleService.save(ADMIN_ROLE);


            ADMIN_USER.setRole(ADMIN_ROLE);
            if (appUserService.existsByUsername(ADMIN_USER.getUsername()))
                ADMIN_USER = appUserService.getByUsername(ADMIN_USER.getUsername());
            else
                ADMIN_USER = appUserService.saveUser(ADMIN_USER);

            FOOD_PROVIDER_USER.setRole(FOOD_PROVIDER_ROLE);
            if (appUserService.existsByUsername(FOOD_PROVIDER_USER.getUsername()))
                FOOD_PROVIDER_USER = appUserService.getByUsername(FOOD_PROVIDER_USER.getUsername());
            else
                FOOD_PROVIDER_USER = appUserService.saveUser(FOOD_PROVIDER_USER);

            ingredientService.save(new Ingredient(1L, "Meat", 25.75F));
            ingredientService.save(new Ingredient(2L, "Bacon", 4.03F));
            ingredientService.save(new Ingredient(3L, "Snails", 0.73F));

            Meal meal = new Meal();
            meal.setName("test");
            meal.setAmount(50);
            mealService.save(meal);
        };
    }
}
