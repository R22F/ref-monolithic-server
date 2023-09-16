package com.example.refmonolithicserver.food.service;

import com.example.refmonolithicserver.common.exception.BusinessException;
import com.example.refmonolithicserver.common.exception.ErrorCode;
import com.example.refmonolithicserver.food.dao.FoodRepository;
import com.example.refmonolithicserver.food.domain.Food;
import com.example.refmonolithicserver.food.dto.FoodDto.FoodWithRecipesResponseDto;
import com.example.refmonolithicserver.ingredient.dao.IngredientRepository;
import com.example.refmonolithicserver.ingredient.domain.Ingredient;
import com.example.refmonolithicserver.recipe.dao.RecipeRepository;
import com.example.refmonolithicserver.recipe.domain.Recipe;
import com.example.refmonolithicserver.recipe.dto.RecipeDto.RecipeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.refmonolithicserver.common.exception.ExceptionMessage.FOOD_NOT_FOUND;
import static com.example.refmonolithicserver.common.exception.ExceptionMessage.INGREDIENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class FoodReadService {

    private final FoodRepository foodRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public Object getAll(String username) {
        // userId를 찾아 대입
        return foodRepository.findAllByUserId(username);
    }

    public Object getItem(Long id) {
        return foodRepository.findById(id);
    }

    public Object getRecipeByFoodId(Long foodId) {

        var food = foodRepository.findById(foodId).orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, FOOD_NOT_FOUND));

        List<Recipe> recipes = recipeRepository.findByFoodId(foodId);
        List<RecipeResponseDto> responses = new ArrayList<>();
        double primePrice = 0.0;
        for(Recipe recipe:recipes){
            Ingredient ingredient = ingredientRepository
                    .findById(recipe.getIngredientId())
                    .orElseThrow(()->new BusinessException(ErrorCode.NOT_FOUND, INGREDIENT_NOT_FOUND));
            responses.add(new RecipeResponseDto().toDto(ingredient));
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

    public Object getRecipesAll(String username) {
        List<Food> foods = foodRepository.findAllByUserId(username);
        var recipes = new ArrayList<>();
        for (Food food:foods) {
            recipes.add(getRecipeByFoodId(food.getId()));
        }
        return recipes;
    }
}
