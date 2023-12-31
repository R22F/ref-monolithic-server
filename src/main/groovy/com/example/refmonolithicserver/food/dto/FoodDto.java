package com.example.refmonolithicserver.food.dto;

import com.example.refmonolithicserver.food.domain.Food;
import com.example.refmonolithicserver.recipe.dto.RecipeDto.RecipeResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public record FoodDto() {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FoodRequestDto{
        private String name;
        private Integer fixedPrice;

        public Food toEntity(String username){
            return Food.builder()
                    .name(this.name)
                    .fixedPrice(this.fixedPrice)
                    .username(username)
                    .build();
        }
        public Food toEntity(String username, Long id){
            return Food.builder()
                    .id(id)
                    .name(this.name)
                    .fixedPrice(this.fixedPrice)
                    .username(username)
                    .build();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FoodWithRecipesResponseDto{
        private Long id;
        private String name;
        private Integer fixedPrice;
        private Double primePrice;
        private List<RecipeResponseDto> recipes;
    }
}
