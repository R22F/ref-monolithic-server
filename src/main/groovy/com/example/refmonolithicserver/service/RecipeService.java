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
        FoodDto foodDto = new FoodDto(1L, "Potato Pizza", 16000, 5000, List.of(ingredientDto, ingredientDto, ingredientDto));
        return new RecipeResponseDto(
                List.of(foodDto, foodDto, foodDto, foodDto));
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
        IngredientDto ingredientDto = new IngredientDto("potato", 200, "g");
        FoodDto foodDto = new FoodDto(foodId, "Potato Pizza", 16000, 5000, List.of(ingredientDto, ingredientDto, ingredientDto));
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
