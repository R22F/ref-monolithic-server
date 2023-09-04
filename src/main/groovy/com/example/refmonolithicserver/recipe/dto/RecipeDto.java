package com.example.refmonolithicserver.recipe.dto;

import com.example.refmonolithicserver.recipe.domain.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public record RecipeDto() {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RecipeRequestDto{
        private Integer quantity;
        private Long foodId;
        private Long ingredientId;

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
        private String units;
    }
}
