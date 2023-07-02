package com.example.EcoHack.app.Ingredient;

import com.example.EcoHack.app.Ingredient.DTO.IngredientDTO;
import com.example.EcoHack.app.Ingredient.DTO.IngredientsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/public")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/ingredient")
    public ResponseEntity<IngredientsDTO> getIngredients() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @GetMapping("/ingredient/{id}")
    public ResponseEntity<IngredientDTO> getIngredient(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientService.getIngredientById(id));
    }
}
