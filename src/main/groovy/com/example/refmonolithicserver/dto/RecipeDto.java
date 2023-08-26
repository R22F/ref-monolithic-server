package com.example.refmonolithicserver.dto;

import com.example.refmonolithicserver.domain.Food;
import com.example.refmonolithicserver.domain.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

public record RecipeDto() {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class RecipeRequestDto{
        List<FoodDto> foodDtoList;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class RecipeResponseDto{
        List<FoodDto> foodDtoList;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class FoodRequestDto{
        private FoodDto foodDto;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class FoodResponseDto{
        private FoodDto foodDto;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FoodDto {
        private Long id;
        private String name;
        private Integer fixedPrice;
        private Integer primePrice;
        private List<IngredientDto> ingredients;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IngredientDto {
        private String name;
        private Integer quantity;
        private String units;
    }
}
