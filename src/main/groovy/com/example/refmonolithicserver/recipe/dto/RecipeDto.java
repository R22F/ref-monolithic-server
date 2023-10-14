package com.example.refmonolithicserver.recipe.dto;

import com.example.refmonolithicserver.ingredient.domain.Ingredient;
import com.example.refmonolithicserver.recipe.domain.Recipe;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public record RecipeDto() {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RecipeRequestDto{
        @NotNull(message = "quantity : not null") private Integer quantity;
        @NotNull(message = "foodId : not null") private Long foodId;
        @NotNull(message = "ingredientId : not null") private Long ingredientId;

        public Recipe toEntity(String ingredientName){
            return Recipe.builder()
                    .quantity(this.quantity)
                    .foodId(this.foodId)
                    .ingredientId(this.ingredientId)
                    .ingredientName(ingredientName)
                    .build();
        }
        public Recipe toEntity(String ingredientName, Long recipeId){
            return Recipe.builder()
                    .id(recipeId)
                    .quantity(this.quantity)
                    .foodId(this.foodId)
                    .ingredientId(this.ingredientId)
                    .ingredientName(ingredientName)
                    .build();
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecipeResponseDto {
        private Long id;
        private Integer quantity;
        private String ingredientName;
        private Long ingredientId;
        private String units;

        public RecipeResponseDto toDto(Ingredient ingredient){
            return RecipeResponseDto.builder()
                    .id(this.id)
                    .quantity(this.quantity)
                    .ingredientName(ingredient.getName())
                    .ingredientId(ingredient.getId())
                    .units(ingredient.getUnits())
                    .build();
        }
        public RecipeResponseDto toDto(Ingredient ingredient, Recipe recipe){
            return RecipeResponseDto.builder()
                    .id(recipe.getId())
                    .quantity(recipe.getQuantity())
                    .ingredientName(ingredient.getName())
                    .ingredientId(ingredient.getId())
                    .units(ingredient.getUnits())
                    .build();
        }
    }
}
