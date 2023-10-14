package com.example.refmonolithicserver.ingredient.service;

import com.example.refmonolithicserver.common.exception.BusinessException;
import com.example.refmonolithicserver.common.exception.ErrorCode;
import com.example.refmonolithicserver.common.exception.ExceptionMessage;
import com.example.refmonolithicserver.ingredient.dao.IngredientRepository;
import com.example.refmonolithicserver.recipe.dao.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.refmonolithicserver.ingredient.dto.IngredientDto.IngredientRequestDto;

@Service
@Transactional
@RequiredArgsConstructor
public class InventoryWriteService {
    
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;

    public Object addItem(IngredientRequestDto dto, String username) {
        return ingredientRepository.save(dto.toEntity(username));
    }

    public Object modifyItem(Long id, IngredientRequestDto dto, String username){
        return ingredientRepository.save(dto.toEntity(id, username));
    }

    public Long removeItem(Long id) {
        if (recipeRepository.existsByIngredientId(id))
            throw new BusinessException(ErrorCode.INVALID_PARAMETER, ExceptionMessage.RECIPE_ALREADY_EXIST);
        ingredientRepository.deleteById(id);
        return id;
    }
}
