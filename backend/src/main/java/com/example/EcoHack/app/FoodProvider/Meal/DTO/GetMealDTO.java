package com.example.EcoHack.app.FoodProvider.Meal.DTO;

import com.example.EcoHack.app.FoodProvider.Cuisine.Cuisine;
import com.example.EcoHack.app.FoodProvider.Meal.Meal;
import com.example.EcoHack.app.FoodProvider.Meal.MealIngredient;
import com.example.EcoHack.app.FoodProvider.Meal.MealService;
import com.example.EcoHack.util.localFileStore.FileDTO;
import com.example.EcoHack.util.localFileStore.LocalFile;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class GetMealDTO extends  GetModerateMealDTO{
    private Set<MealIngredient> ingredients;

    private Cuisine cuisine;
    public GetMealDTO(Meal m) {
        super(m);
        this.cuisine=m.getCuisine();
        this.ingredients = m.getIngredients();
    }
}
