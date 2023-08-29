package com.example.refmonolithicserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.refmonolithicserver.dto.RecipeDto.*;

@Service
@RequiredArgsConstructor
public class RecipeService {

    public RecipeResponseDto getRecipe() {
        IngredientDto ingredientDto = new IngredientDto("potato", 200, "g");
        FoodDto foodDto1 = new FoodDto(1L, "Potato Pizza", 16000, 5000, List.of(ingredientDto, ingredientDto));
        FoodDto foodDto2 = new FoodDto(2L, "Fried Chicken", 18000, 7000, List.of(ingredientDto, ingredientDto));
        FoodDto foodDto3 = new FoodDto(3L, "Burrito", 4500, 1500, List.of(ingredientDto, ingredientDto));
        return new RecipeResponseDto(
                List.of(foodDto1, foodDto2, foodDto3)
        );
    }

    public RecipeResponseDto addRecipe(RecipeRequestDto dto) {
        return new RecipeResponseDto(dto.getFoodDtoList());
    }

    public RecipeResponseDto modifyRecipe(RecipeRequestDto dto) {
        return new RecipeResponseDto(dto.getFoodDtoList());
    }

    public Long removeRecipe(Long id) {
        return id;
    }

    public FoodResponseDto getFoodInRecipe(Long foodId) {
        IngredientDto ingredientDto1 = new IngredientDto("potato", 200, "g");
        IngredientDto ingredientDto2 = new IngredientDto("cheese", 100, "g");
        IngredientDto ingredientDto3 = new IngredientDto("olive", 10, "g");
        FoodDto foodDto = new FoodDto(
                foodId, "Potato Pizza", 16000, 5000,
                List.of(ingredientDto1, ingredientDto2, ingredientDto3));
        return new FoodResponseDto(foodDto);
    }

    public FoodResponseDto addFoodInRecipe(Long foodId, FoodRequestDto dto) {
        FoodDto foodDto = dto.getFoodDto();
        foodDto.setId(foodId);
        return new FoodResponseDto(foodDto);
    }

    public Long removeFoodInRecipe(Long foodId) {
        return foodId;
    }
}
