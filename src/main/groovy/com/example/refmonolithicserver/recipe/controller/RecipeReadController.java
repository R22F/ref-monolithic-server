package com.example.refmonolithicserver.recipe.controller;

import com.example.refmonolithicserver.recipe.service.RecipeReadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/recipe")
@Tag(name = "RecipeReadController",description = "RecipeAPI-재료들의 조합")
public class RecipeReadController {

    private final RecipeReadService recipeService;

    @GetMapping("/{recipeId}")
    @Operation(summary = "Id로 레시피 찾기")
    public ResponseEntity<?> retrieveRecipe(
            @PathVariable(value = "recipeId") Long recipeId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                recipeService.getRecipeById(recipeId)
        );
    }

    @GetMapping("/ingredient/{ingredientId}")
    @Operation(summary = "해당 ingredient 정보를 가지고 있는 레시피 존재 여부 확인")
    public ResponseEntity<?> retrieveIngredientExist(
            @PathVariable(value = "ingredientId") Long ingredientId
    ){
        return ResponseEntity.status(HttpStatus.OK).body(
                recipeService.checkIngredient(ingredientId)
        );
    }
}