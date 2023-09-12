package com.example.refmonolithicserver.recipe.service;

import com.example.refmonolithicserver.common.exception.BusinessException;
import com.example.refmonolithicserver.common.exception.ErrorCode;
import com.example.refmonolithicserver.ingredient.dao.IngredientRepository;
import com.example.refmonolithicserver.recipe.dao.RecipeRepository;
import com.example.refmonolithicserver.recipe.domain.Recipe;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeReadService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public Object getRecipeById(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(()-> new BusinessException(ErrorCode.NOT_FOUND, "레시피 ID에 대응되는 레시피가 존재하지 않습니다"));
        String ingredientName = ingredientRepository
                .findById(recipe.getIngredientId())
                .orElseThrow(()->new BusinessException(ErrorCode.NOT_FOUND, "재료 ID에 대응되는 재료가 존재하지 않습니다"))
                .getName();
        recipe.setIngredientName(ingredientName);
        return recipe;
    }
}
