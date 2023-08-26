package com.example.refmonolithicserver.controller;

import com.example.refmonolithicserver.common.ResponseMessage;
import com.example.refmonolithicserver.dto.RecipeDto.FoodRequestDto;
import com.example.refmonolithicserver.dto.RecipeDto.RecipeRequestDto;
import com.example.refmonolithicserver.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/recipe")
public class RecipeController {

    private final RecipeService recipeService;
    private final ResponseMessage<Object> responseMessage;

    @GetMapping
    @Operation(summary = "Retrieve recipe")
    public ResponseEntity<?> retrieveRecipe() {
        return ResponseEntity.status(HttpStatus.OK).body(
                recipeService.getRecipe()
        );
    }

    @PostMapping
    @Operation(summary = "Create recipe")
    public ResponseEntity<?> createRecipe(@RequestBody RecipeRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                recipeService.addRecipe(dto)
        );
    }

    @PutMapping
    @Operation(summary = "Update recipe")
    public ResponseEntity<?> updateRecipe(@RequestBody RecipeRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(recipeService.modifyRecipe(dto)
        );
    }

    @DeleteMapping()
    @Operation(summary = "Delete recipe")
    public ResponseEntity<?> deleteRecipe(@RequestBody Long recipeId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseMessage.removeMessage(
                        recipeService.removeRecipe(recipeId))
                );
    }

    @GetMapping("/{foodId}")
    @Operation(summary = "Retrieve food in recipe")
    public ResponseEntity<?> retrieveFood(@PathVariable("foodId") Long foodId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(recipeService.getFoodInRecipe(foodId));
    }

    @PostMapping("/{foodId}")
    @Operation(summary = "Create food in recipe")
    public ResponseEntity<?> addFood(
            @PathVariable("foodId") Long foodId,
            @RequestBody FoodRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(recipeService.addFoodInRecipe(foodId, dto));
    }


    @DeleteMapping("/{foodId}")
    @Operation(summary = "Delete recipe")
    public ResponseEntity<?> deleteFood(@PathVariable("foodId") Long foodId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        responseMessage.removeMessage(recipeService.removeFoodInRecipe(foodId))
                );
    }
}