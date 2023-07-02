package com.example.EcoHack.app.Ingredient;

import com.example.EcoHack.app.Ingredient.DTO.IngredientDTO;
import com.example.EcoHack.app.Ingredient.DTO.IngredientsDTO;
import com.example.EcoHack.app.Ingredient.mapper.IngredientMapper;
import com.example.EcoHack.common.BaseService;
import com.example.EcoHack.exceptions.api.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientService extends BaseService<Ingredient> {

    @Autowired
    private IngredientMapper mapper;
    @Autowired
    private IngredientRepository ingredientRepository;
    public IngredientService(IngredientRepository ingredientRepository) {
        super(ingredientRepository, "Ingredient");
    }


    public IngredientsDTO getAllIngredients() {
        List<IngredientDTO> ingredients = ingredientRepository.findAll().stream().map(mapper).collect(Collectors.toList());
        return new IngredientsDTO(
                ingredients,
                ingredients.size()
        );
    }

    public IngredientDTO getIngredientById(Long id) {
        return ingredientRepository.findById(id).map(mapper).orElseThrow(() -> new ResourceNotFoundException("Ingredient", "id", id));
    }
}
