package com.example.EcoHack.app.FoodProvider;

import com.example.EcoHack.app.FoodProvider.Meal.Meal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@Entity
@Table
public class FoodProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private Set<Meal> meals;
}
