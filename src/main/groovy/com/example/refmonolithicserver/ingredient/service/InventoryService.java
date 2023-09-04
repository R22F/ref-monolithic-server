package com.example.refmonolithicserver.ingredient.service;

import com.example.refmonolithicserver.ingredient.dao.IngredientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.refmonolithicserver.ingredient.dto.IngredientDto.IngredientRequestDto;

@Service
@Transactional
@RequiredArgsConstructor
public class InventoryService {
    
    private final IngredientRepository ingredientRepository;

    public Object getAll() {
        // userId 가져오기
        return ingredientRepository.findAllByUserId(1L);
    }

    public Object getItem(Long id) {
        return ingredientRepository.findById(id);
    }

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
