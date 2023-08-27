package com.example.refmonolithicserver.service;

import com.example.refmonolithicserver.dto.RecipeDto.IngredientRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.example.refmonolithicserver.dto.RecipeDto.IngredientResponseDto;

@Service
@RequiredArgsConstructor
public class InventoryService {
    public IngredientResponseDto getItem(Long id) {
        return new IngredientResponseDto(
                id, "potato", 1000,
                LocalDate.of(23,8,11),
                LocalDate.of(23,8,26),
                200, 1000, 3000, 15,
                "https://www.coupang.com/"
        );
    }

    public Long addItem(IngredientRequestDto dto) {
        return dto.getId();
    }

    public IngredientResponseDto modifyItem(Long id, IngredientRequestDto dto) {
        return new IngredientResponseDto(
                id, dto.getName(), dto.getBuyQuantity(),
                dto.getBuyDate(),
                dto.getBuyDate().plusDays(dto.getExpiredPeriod()),
                dto.getAlertQuantity(),
                dto.getRelievedQuantity(),
                dto.getPrimePrice(), dto.getExpiredPeriod(), dto.getUrl()
                );
    }

    public Long removeItem(Long id) {
        return id;
    }
}
