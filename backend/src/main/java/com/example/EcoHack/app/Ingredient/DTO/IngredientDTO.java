package com.example.EcoHack.app.Ingredient.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IngredientDTO {
    Long id;
    String name;
    Float footprintPerKg;
}
