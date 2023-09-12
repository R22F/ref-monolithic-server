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

    public Object addItem(IngredientRequestDto dto) {
        return ingredientRepository.save(dto.toEntity());
    }

    public Object modifyItem(Long id, IngredientRequestDto dto){
        return dto.toEntity(id);
    }

    public Long removeItem(Long id) {
        ingredientRepository.deleteById(id);
        return id;
    }
}
