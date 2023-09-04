package com.example.refmonolithicserver.food.service;

import com.example.refmonolithicserver.common.exception.BusinessException;
import com.example.refmonolithicserver.common.exception.ErrorCode;
import com.example.refmonolithicserver.food.domain.Food;
import com.example.refmonolithicserver.ingredient.domain.Ingredient;
import com.example.refmonolithicserver.recipe.domain.Recipe;
import com.example.refmonolithicserver.food.dto.FoodDto.FoodWithRecipesResponseDto;
import com.example.refmonolithicserver.recipe.dto.RecipeDto.RecipeResponseDto;
import com.example.refmonolithicserver.food.dao.FoodRepository;
import com.example.refmonolithicserver.ingredient.dao.IngredientRepository;
import com.example.refmonolithicserver.recipe.dao.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodReadService {

    private final FoodRepository foodRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public Object getAll() {
        // userId를 찾아 대입
        return foodRepository.findAllByUserId(1L);
    }

    public Object getItem(Long id) {
        return foodRepository.findById(id);
    }

    public Object getRecipeByFoodId(Long foodId) {

        var food = foodRepository.findById(foodId).orElseThrow(() -> new BusinessException(
                ErrorCode.NOT_FOUND, "해당 FoodId에 해당하는 데이터가 없습니다"));

        List<Recipe> recipes = recipeRepository.findByFoodId(foodId);
        List<RecipeResponseDto> responses = new ArrayList<>();
        double primePrice = 0.0;
        for(Recipe recipe:recipes){
            Ingredient ingredient = ingredientRepository
                    .findById(recipe.getIngredientId())
                    .orElseThrow(()->new BusinessException(ErrorCode.NOT_FOUND, "재료 ID에 대응되는 재료가 존재하지 않습니다"));
            responses.add(recipe.toResponse(ingredient));
            primePrice += recipe.getQuantity() * ingredient.getPrimePrice();
        }
        // 원가 = food -> recipe -> recipe.quantity * ingredient.primePrice

        return new FoodWithRecipesResponseDto(
                foodId,
                food.getName(),
                food.getFixedPrice(),
                primePrice,
                responses);
    }

    public Object getRecipesAll() {
        List<Food> foods = foodRepository.findAllByUserId(1L);
        var recipes = new ArrayList<>();
        for (Food food:foods) {
            recipes.add(getRecipeByFoodId(food.getId()));
        }
        return recipes;
    }
}
