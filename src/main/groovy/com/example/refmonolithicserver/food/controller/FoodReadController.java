package com.example.refmonolithicserver.food.controller;

import com.example.refmonolithicserver.food.service.FoodReadService;
import com.example.refmonolithicserver.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/food")
@Tag(name = "FoodReadController",description = "FoodAPI-판매 가능한 음식과 관련된 조회")
public class FoodReadController {

    private final FoodReadService foodService;

    @GetMapping
    @Operation(summary = "전체 음식 종류를 출력")
    public ResponseEntity<?> retrieveAll(
            Principal principal
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(foodService.getAll(principal.getName()));
    }

    @GetMapping("/recipes")
    @Operation(summary = "전체 레시피 정보 출력")
    public ResponseEntity<?> retrieveRecipesAll(
            Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(
                foodService.getRecipesAll(principal.getName())
        );
    }

    @GetMapping("/{foodId}/recipes")
    @Operation(summary = "해당 음식에 대한 전체 레시피 정보 출력")
    public ResponseEntity<?> retrieveRecipesByFoodId(
            @PathVariable(value = "foodId") Long foodId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                foodService.getRecipeByFoodId(foodId)
        );
    }

    @GetMapping("/{foodId}")
    @Operation(summary = "Id로 음식 찾기")
    public ResponseEntity<?> retrieveIngredient(
            @PathVariable("foodId") Long id
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(foodService.getItem(id));
    }
}
