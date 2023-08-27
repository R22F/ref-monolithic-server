package com.example.refmonolithicserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IngredientResponseDto {
        private Long id;
        private String name;
        private Integer remainQuantity;
        private LocalDate buyDate;
        private LocalDate expiredDate;
        private Integer alertQuantity;
        private Integer relievedQuantity;
        private Integer primePrice;
        private Integer expiredPeriod;
        private String url;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IngredientRequestDto {
        private Long id;
        private String name;
        private Integer buyQuantity;
        private LocalDate buyDate;
        private Integer alertQuantity;
        private Integer relievedQuantity;
        private Integer primePrice;
        private Integer expiredPeriod;
        private String url;
    }
}
