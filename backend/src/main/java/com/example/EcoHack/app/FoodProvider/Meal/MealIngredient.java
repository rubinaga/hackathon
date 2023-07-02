package com.example.EcoHack.app.FoodProvider.Meal;

import com.example.EcoHack.app.Ingredient.Ingredient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class MealIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "meal_id")
    private Meal meal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private int weight;

    public MealIngredient(Meal meal, Ingredient ingredient) {
        this.meal = meal;
        this.ingredient = ingredient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealIngredient)) return false;
        MealIngredient that = (MealIngredient) o;
        return Objects.equals(meal.getId(), that.meal.getId()) &&
                Objects.equals(ingredient.getId(), that.ingredient.getId()) &&
                Objects.equals(weight, that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meal.getId(), ingredient.getId(), weight);
    }
}
