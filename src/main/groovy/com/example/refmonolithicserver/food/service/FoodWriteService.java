package com.example.refmonolithicserver.food.service;

import com.example.refmonolithicserver.food.dto.FoodDto.FoodRequestDto;
import com.example.refmonolithicserver.food.dao.FoodRepository;
import com.example.refmonolithicserver.recipe.dao.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodWriteService {

    private final FoodRepository foodRepository;
    private final RecipeRepository recipeRepository;

    public Object addItem(FoodRequestDto dto) {
        return foodRepository.save(dto.toEntity(1L));
    }

    public Object modifyItem(Long id,  FoodRequestDto dto) {
        return foodRepository.save(dto.toEntity(1L, id));
    }

    public Long removeItem(Long id) {
        foodRepository.deleteById(id);
        return id;
    }

    public Long removeRecipeByFood(Long foodId) {

        recipeRepository.deleteByFoodId(foodId);
        return foodId;
    }
}
