package com.example.refmonolithicserver.ingredient.service;

import com.example.refmonolithicserver.ingredient.dao.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryReadService {
    
    private final IngredientRepository ingredientRepository;

    public Object getAll(String username) {
        return ingredientRepository.findAllByUserId(username);
    }

    public Object getItem(Long id) {
        return ingredientRepository.findById(id);
    }
}
