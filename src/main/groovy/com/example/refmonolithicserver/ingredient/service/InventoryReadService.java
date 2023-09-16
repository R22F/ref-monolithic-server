package com.example.refmonolithicserver.ingredient.service;

import com.example.refmonolithicserver.ingredient.dao.IngredientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryReadService {
    
    private final IngredientRepository ingredientRepository;

    public Object getAll() {
        // userId 가져오기
        return ingredientRepository.findAllByUserId(1L);
    }

    public Object getItem(Long id) {
        return ingredientRepository.findById(id);
    }
}
