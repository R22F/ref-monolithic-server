package com.example.refmonolithicserver.service;

import com.example.refmonolithicserver.repository.IngredientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.refmonolithicserver.dto.IngredientDto.IngredientRequestDto;

@Service
@Transactional
@RequiredArgsConstructor
public class InventoryService {
    
    private final IngredientRepository ingredientRepository;

    public Object getAll() {
        return ingredientRepository.findAll();
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
