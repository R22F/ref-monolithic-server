package com.example.refmonolithicserver.service;

import com.example.refmonolithicserver.domain.Recipe;
import com.example.refmonolithicserver.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.refmonolithicserver.dto.RecipeDto.*;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public Object getRecipe() {
        return recipeRepository.findAll();
    }

    public Object getRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId);
    }

    public Object addRecipe(List<RecipeRequestDto> requestDtoList) {
        // food, ingredient 검증 로직 추가 필요
        List<Recipe> savedRecipeList = new ArrayList<>();
        for(RecipeRequestDto dto:requestDtoList){
            savedRecipeList.add(recipeRepository.save(dto.toEntity()));
        }
        return savedRecipeList;
    }

    public Object modifyRecipe(Long recipeId, RecipeRequestDto dto) {
        // food, ingredient 검증 로직 추가 필요
        return recipeRepository.save(dto.toEntity(recipeId));
    }

    public Long removeRecipe(Long id) {
        recipeRepository.deleteById(id);
        return id;
    }

    public Long removeRecipeByFood(Long foodId) {
        recipeRepository.deleteByFoodId(foodId);
        return foodId;
    }
}
