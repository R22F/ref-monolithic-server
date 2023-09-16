package com.example.refmonolithicserver.ingredient.service;

import com.example.refmonolithicserver.ingredient.dao.IngredientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.refmonolithicserver.ingredient.dto.IngredientDto.IngredientRequestDto;

@Service
@Transactional
@RequiredArgsConstructor
public class InventoryWriteService {
    
    private final IngredientRepository ingredientRepository;

    public Object addItem(IngredientRequestDto dto, String username) {
        return ingredientRepository.save(dto.toEntity(username));
    }

    public Object modifyItem(Long id, IngredientRequestDto dto, String username){
        return dto.toEntity(id, username);
    }

    public Long removeItem(Long id) {
        ingredientRepository.deleteById(id);
        return id;
    }
}
