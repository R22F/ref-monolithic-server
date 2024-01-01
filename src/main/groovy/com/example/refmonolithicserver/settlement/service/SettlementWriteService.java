package com.example.refmonolithicserver.settlement.service;

import com.example.refmonolithicserver.common.exception.BusinessException;
import com.example.refmonolithicserver.common.exception.ErrorCode;
import com.example.refmonolithicserver.food.dao.FoodRepository;
import com.example.refmonolithicserver.food.domain.Food;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.refmonolithicserver.common.exception.ExceptionMessage.INGREDIENT_NOT_FOUND;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SettlementWriteService {

    private final SalesHistoryRepository salesHistoryRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;

    public LocalDate set(SettlementRequestDto dto, String username) {

        LocalDate date = dto.getReqDate();
        for (FoodInfo info: dto.getFoods()){
            Optional<SalesHistory> history = salesHistoryRepository.findByFoodNameAndSalesDate(info.getName(), date);
            int originQuantity = 0;
            if (history.isPresent()){
                SalesHistory salesHistory = history.get();
                originQuantity = salesHistory.getCount();
                salesHistory.modifyInfo(info.getCount(), info.getNote(), info.getFixedPrice());
            }
            else {
                List<Recipe> recipes = recipeRepository.findByFoodId(info.getId());
                double primePrice = 0.0;
                for (Recipe recipe:recipes){
                    Ingredient ingredient = ingredientRepository.findById(
                            recipe.getIngredientId()).orElseThrow(
                            () -> new BusinessException(ErrorCode.NOT_FOUND, INGREDIENT_NOT_FOUND));
                    primePrice += ingredient.getPrimePrice() * recipe.getQuantity();
                }
                salesHistoryRepository.save(info.toEntity(username, date, primePrice));
            }
            /* @brief
             *  1. SettlementRequestDto->foodInfo->foodId를 가져옴
             *  2. 해당 foodId를 사용 하는 recipe 를 가져옴
             *  3. recipe 에서 ingredientId 를 가져옴
             *  4. ingredient 에서 quantity 를 수정함
             *   : fixedQuantity = ingredient.remainQuantity - recipe.quantity
             *   : fixedQuantity 를 alertQuantity 와 비교
             */
            List<Recipe> recipes = recipeRepository.findByFoodId(info.getId());
            for (Recipe recipe:recipes){
                Ingredient ingredient = ingredientRepository.findById(
                        recipe.getIngredientId()).orElseThrow(
                                () -> new BusinessException(ErrorCode.NOT_FOUND, INGREDIENT_NOT_FOUND));
                int fixedQuantity = ingredient.getRemainQuantity() - (info.getCount()-originQuantity)*recipe.getQuantity();
                ingredientRepository.save(ingredient.modifyQuantity(fixedQuantity));
            }
        }

        return date;
    }

    public SalesHistory update(SalesRequestDto dto, String username) {

        salesHistoryRepository.save(dto.toEntity(username));

        List<Recipe> recipes = recipeRepository.findByFoodId(dto.getFoodId());
        for (Recipe recipe:recipes){
            Ingredient ingredient = ingredientRepository.findById(
                    recipe.getIngredientId()).orElseThrow(
                    () -> new BusinessException(ErrorCode.NOT_FOUND, INGREDIENT_NOT_FOUND));
            int fixedQuantity = ingredient.getRemainQuantity() - recipe.getQuantity();
            ingredientRepository.save(ingredient.modifyQuantity(fixedQuantity));
        }
        return dto.toEntity(username);
    }

    public Long remove(Long salesId) {

        salesHistoryRepository.deleteById(salesId);

        return salesId;
    }
}
