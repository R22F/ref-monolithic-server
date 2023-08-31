package com.example.refmonolithicserver.dto;

import com.example.refmonolithicserver.domain.Recipe;
import lombok.AllArgsConstructor;
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

        public Recipe toEntity(){
            return Recipe.builder()
                    .quantity(this.quantity)
                    .foodId(this.foodId)
                    .ingredientId(this.ingredientId)
                    .build();
        }
        public Recipe toEntity(Long recipeId){
            return Recipe.builder()
                    .id(recipeId)
                    .quantity(this.quantity)
                    .foodId(this.foodId)
                    .ingredientId(this.ingredientId)
                    .build();
        }
    }
}
