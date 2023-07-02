package com.example.EcoHack.app;


import com.example.EcoHack.app.Ingredient.Ingredient;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="Diet")
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name="diet_preferred_ingredients",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "diet_id")
    )
    private Set<Ingredient> preferredingredients;

    @ManyToMany
    @JoinTable(
            name="diet_restricted_ingredients",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "diet_id")
    )
    private Set<Ingredient> restrictedingredients;
}
