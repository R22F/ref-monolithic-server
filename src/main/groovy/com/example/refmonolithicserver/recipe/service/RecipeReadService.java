package com.example.refmonolithicserver.recipe.service;

import com.example.refmonolithicserver.common.exception.BusinessException;
import com.example.refmonolithicserver.common.exception.ErrorCode;
import com.example.refmonolithicserver.ingredient.dao.IngredientRepository;
import com.example.refmonolithicserver.recipe.dao.RecipeRepository;
import com.example.refmonolithicserver.recipe.domain.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.refmonolithicserver.common.exception.ExceptionMessage.INGREDIENT_NOT_FOUND;
import static com.example.refmonolithicserver.common.exception.ExceptionMessage.RECIPE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class RecipeReadService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public Object getRecipeById(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(()-> new BusinessException(ErrorCode.NOT_FOUND, RECIPE_NOT_FOUND));
        String ingredientName = ingredientRepository
                .findById(recipe.getIngredientId())
                .orElseThrow(()->new BusinessException(ErrorCode.NOT_FOUND, INGREDIENT_NOT_FOUND))
                .getName();
        recipe.setIngredientName(ingredientName);
        return recipe;
    }
}
