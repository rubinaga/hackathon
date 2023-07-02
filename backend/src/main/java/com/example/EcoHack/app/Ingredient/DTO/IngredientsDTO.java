package com.example.EcoHack.app.Ingredient.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class IngredientsDTO {
    List<IngredientDTO> ingredients;
    int size;
}
