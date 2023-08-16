package com.example.refmonolithicserver.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Recipe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long id;

    @Column
    private LocalDate createDate;

    @Column Integer quantity;

    @ManyToOne @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
}
