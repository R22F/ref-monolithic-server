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
public class Recipe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id") private Long id;
    @Column Integer quantity;
    @Column private Long foodId;
    @Column private Long ingredientId;
    @Transient @Setter private String ingredientName;

    public RecipeResponseDto toResponse(Ingredient ingredient){
        return RecipeResponseDto.builder()
                .id(this.id)
                .quantity(this.quantity)
                .ingredientName(ingredient.getName())
                .units(ingredient.getUnits())
                .build();
    }
    public RecipeResponseDto toResponse(Ingredient ingredient, Long recipeId){
        return RecipeResponseDto.builder()
                .id(recipeId)
                .quantity(this.quantity)
                .ingredientName(ingredient.getName())
                .units(ingredient.getUnits())
                .build();
    }
}
