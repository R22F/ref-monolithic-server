package com.example.refmonolithicserver.recipe.service;

import com.example.refmonolithicserver.common.exception.BusinessException;
import com.example.refmonolithicserver.common.exception.ErrorCode;
import com.example.refmonolithicserver.recipe.domain.Recipe;
import com.example.refmonolithicserver.ingredient.dao.IngredientRepository;
import com.example.refmonolithicserver.recipe.dao.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.refmonolithicserver.recipe.dto.RecipeDto.RecipeRequestDto;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeService {

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

    public Object addRecipe(List<RecipeRequestDto> requestDtoList) {

        // food, ingredient 검증 로직 추가 필요
        List<Recipe> savedRecipeList = new ArrayList<>();
        for(RecipeRequestDto dto:requestDtoList){
            String ingredientName = ingredientRepository
                    .findById(dto.getIngredientId())
                    .orElseThrow(()->new BusinessException(ErrorCode.NOT_FOUND, "재료 ID에 대응되는 재료가 존재하지 않습니다"))
                    .getName();
            savedRecipeList.add(recipeRepository.save(dto.toEntity(ingredientName)));
        }
        return savedRecipeList;
    }

    public Object modifyRecipe(Long recipeId, RecipeRequestDto dto) {

        // food, ingredient 검증 로직 추가 필요
        String ingredientName = ingredientRepository
                .findById(dto.getIngredientId())
                .orElseThrow(()->new BusinessException(ErrorCode.NOT_FOUND, "재료 ID에 대응되는 재료가 존재하지 않습니다"))
                .getName();
        return recipeRepository.save(dto.toEntity(ingredientName, recipeId));
    }

    public Long removeRecipe(Long id) {

        recipeRepository.deleteById(id);
        return id;
    }
}
