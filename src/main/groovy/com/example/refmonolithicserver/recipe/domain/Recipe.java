package com.example.refmonolithicserver.recipe.domain;

import com.example.refmonolithicserver.ingredient.domain.Ingredient;
import com.example.refmonolithicserver.recipe.dto.RecipeDto.RecipeResponseDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipe",
        indexes = {
                @Index(name = "idx_food_id", columnList = "food_id"),
                @Index(name = "idx_ingredient_id", columnList = "ingredient_id"),
        })
public class Recipe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;
    @Column Integer quantity;
    @Column(name = "food_id") private Long foodId;
    @Column(name = "ingredient_id") private Long ingredientId;
    @Transient @Setter private String ingredientName;
}
