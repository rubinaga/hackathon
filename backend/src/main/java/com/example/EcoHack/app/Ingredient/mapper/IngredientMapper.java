package com.example.EcoHack.app.Ingredient.mapper;

import com.example.EcoHack.app.Ingredient.DTO.IngredientDTO;
import com.example.EcoHack.app.Ingredient.Ingredient;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class IngredientMapper implements Function<Ingredient, IngredientDTO> {
    @Override
    public IngredientDTO apply(Ingredient ingredient) {
        return new IngredientDTO(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getFootprintPerKg()
        );
    }
}
