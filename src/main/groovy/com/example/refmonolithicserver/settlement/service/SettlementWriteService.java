package com.example.refmonolithicserver.settlement.service;

import com.example.refmonolithicserver.common.exception.BusinessException;
import com.example.refmonolithicserver.common.exception.ErrorCode;
import com.example.refmonolithicserver.ingredient.dao.IngredientRepository;
import com.example.refmonolithicserver.ingredient.domain.Ingredient;
import com.example.refmonolithicserver.recipe.dao.RecipeRepository;
import com.example.refmonolithicserver.recipe.domain.Recipe;
import com.example.refmonolithicserver.settlement.dao.SalesHistoryRepository;
import com.example.refmonolithicserver.settlement.domain.SalesHistory;
import com.example.refmonolithicserver.settlement.dto.SettlementDto.FoodInfo;
import com.example.refmonolithicserver.settlement.dto.SettlementDto.SalesRequestDto;
import com.example.refmonolithicserver.settlement.dto.SettlementDto.SettlementRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.refmonolithicserver.common.exception.ExceptionMessage.INGREDIENT_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class SettlementWriteService {

    private final SalesHistoryRepository salesHistoryRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;

    public LocalDate set(SettlementRequestDto dto) {

        LocalDate today = LocalDate.now();

        for (FoodInfo info: dto.getFoods()){
            salesHistoryRepository.save(info.toEntity(1L));

            // ingredient 정보도 수정 되어야 함

            /* @brief
             *  1. SettlementRequestDto->foodInfo->foodId를 가져옴
             *  2. 해당 foodId를 사용 하는 recipe 를 가져옴
             *  3. recipe 에서 ingredientId 를 가져옴
             *  4. ingredient 에서 quantity 를 수정함
             *   : fixedQuantity = ingredient.remainQuantity - recipe.quantity
             *   : fixedQuantity 를 alertQuantity 와 비교
             */
            List<Recipe> recipes = recipeRepository.findByFoodId(info.getFoodId());
            for (Recipe recipe:recipes){
                Ingredient ingredient = ingredientRepository.findById(
                        recipe.getIngredientId()).orElseThrow(
                                () -> new BusinessException(ErrorCode.NOT_FOUND, INGREDIENT_NOT_FOUND));
                int fixedQuantity = ingredient.getRemainQuantity() - recipe.getQuantity();
                ingredientRepository.save(ingredient.modifyQuantity(fixedQuantity));
            }
        }

        return today;
    }

    public SalesHistory update(SalesRequestDto dto) {

        salesHistoryRepository.save(dto.toEntity(1L));

        List<Recipe> recipes = recipeRepository.findByFoodId(dto.getFoodId());
        for (Recipe recipe:recipes){
            Ingredient ingredient = ingredientRepository.findById(
                    recipe.getIngredientId()).orElseThrow(
                    () -> new BusinessException(ErrorCode.NOT_FOUND, INGREDIENT_NOT_FOUND));
            int fixedQuantity = ingredient.getRemainQuantity() - recipe.getQuantity();
            ingredientRepository.save(ingredient.modifyQuantity(fixedQuantity));
        }
        return dto.toEntity(1L);
    }

    public Long remove(Long salesId) {

        salesHistoryRepository.deleteById(salesId);

        return salesId;
    }
}
