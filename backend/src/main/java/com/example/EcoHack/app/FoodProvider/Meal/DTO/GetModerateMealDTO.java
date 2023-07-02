package com.example.EcoHack.app.FoodProvider.Meal.DTO;

import com.example.EcoHack.app.FoodProvider.Meal.Meal;
import com.example.EcoHack.app.FoodProvider.Meal.MealIngredient;
import com.example.EcoHack.app.FoodProvider.Meal.MealService;
import com.example.EcoHack.common.audit.AuditBaseDTO;
import com.example.EcoHack.util.localFileStore.LocalImage;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ext.SqlBlobSerializer;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Base64;
import java.util.Set;

@Getter
@Setter
public class GetModerateMealDTO extends AuditBaseDTO {
    private Long id;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;


    public GetModerateMealDTO(Meal m) {
        this.id = m.getId();
        try {
            this.image = m.getEncodedImage();
        } catch (UnsupportedEncodingException e) {
            this.image=null;
        }
        this.createdAt = m.getCreatedAt();
        this.updatedAt = m.getUpdatedAt();
        this.price = m.getPrice();
    }
}
