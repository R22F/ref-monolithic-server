package com.example.refmonolithicserver.settlement.service;

import com.example.refmonolithicserver.food.dao.FoodRepository;
import com.example.refmonolithicserver.ingredient.dao.IngredientRepository;
import com.example.refmonolithicserver.recipe.dao.RecipeRepository;
import com.example.refmonolithicserver.settlement.dao.SalesHistoryRepository;
import com.example.refmonolithicserver.settlement.dto.SettlementDto.FoodInfo;
import com.example.refmonolithicserver.settlement.dto.SettlementDto.SettlementRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SettlementService {

    private final SalesHistoryRepository salesHistoryRepository;
    private final FoodRepository foodRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public Object get(LocalDate dto) {
        List<FoodInfo> foodInfos =  new ArrayList<>();
        for (long i = 0L; i < 5; i++) {
            foodInfos.add(new FoodInfo(i, "Pizza", 22000, 30));
        }
        return foodInfos;
    }

    public Object set(SettlementRequestDto dto) {
        return dto;
    }

    public Object update(SettlementRequestDto dto) {
        return dto;
    }

    public Long remove(Long salesId) {
        salesHistoryRepository.deleteById(salesId);

        return salesId;
    }
}
