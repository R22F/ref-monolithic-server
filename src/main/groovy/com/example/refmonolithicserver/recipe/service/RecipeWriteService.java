package com.example.refmonolithicserver.recipe.service;

import com.example.refmonolithicserver.common.exception.BusinessException;
import com.example.refmonolithicserver.common.exception.ErrorCode;
import com.example.refmonolithicserver.food.dao.FoodRepository;
import com.example.refmonolithicserver.ingredient.dao.IngredientRepository;
import com.example.refmonolithicserver.recipe.dao.RecipeRepository;
import com.example.refmonolithicserver.recipe.domain.Recipe;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.refmonolithicserver.common.exception.ExceptionMessage.FOOD_NOT_FOUND;
import static com.example.refmonolithicserver.common.exception.ExceptionMessage.INGREDIENT_NOT_FOUND;
import static com.example.refmonolithicserver.recipe.dto.RecipeDto.RecipeRequestDto;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeWriteService {

    private final RecipeRepository recipeRepository;
    private final FoodRepository foodRepository;
    private final IngredientRepository ingredientRepository;

    public Object addRecipe(List<RecipeRequestDto> requestDtoList) {

        List<Recipe> savedRecipeList = new ArrayList<>();
        for(RecipeRequestDto dto:requestDtoList){
            Long foodId = dto.getFoodId();
            if (!foodRepository.existsById(foodId))
                throw new BusinessException(ErrorCode.NOT_FOUND, FOOD_NOT_FOUND);
            String ingredientName = ingredientRepository
                    .findById(dto.getIngredientId())
                    .orElseThrow(()->new BusinessException(ErrorCode.NOT_FOUND, INGREDIENT_NOT_FOUND))
                    .getName();
            savedRecipeList.add(recipeRepository.save(dto.toEntity(ingredientName)));
        }
        return savedRecipeList;
    }

    public Object modifyRecipe(Long recipeId, RecipeRequestDto dto) {

        // food, ingredient 검증 로직 추가 필요
        String ingredientName = ingredientRepository
                .findById(dto.getIngredientId())
                .orElseThrow(()->new BusinessException(ErrorCode.NOT_FOUND, INGREDIENT_NOT_FOUND))
                .getName();
        return recipeRepository.save(dto.toEntity(ingredientName, recipeId));
    }

    public Long removeRecipe(Long id) {

        recipeRepository.deleteById(id);
        return id;
    }

    public Object removeRecipeByIngredientId(Long ingredientId) {
        recipeRepository.existsByIngredientId(ingredientId);
        return ingredientId;
    }
}
