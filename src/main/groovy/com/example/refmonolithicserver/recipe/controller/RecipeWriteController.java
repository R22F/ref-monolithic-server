package com.example.refmonolithicserver.recipe.controller;

import com.example.refmonolithicserver.common.ResponseMessage;
import com.example.refmonolithicserver.recipe.dto.RecipeDto.RecipeRequestDto;
import com.example.refmonolithicserver.recipe.service.RecipeWriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/recipe")
@Tag(name = "RecipeWriteController",description = "RecipeAPI-재료들의 조합")
public class RecipeWriteController {

    private final RecipeWriteService recipeService;
    private final ResponseMessage responseMessage;

    @PostMapping
    @Operation(summary = "새로운 레시피 정보 생성(리스트)")
    public ResponseEntity<?> createRecipe(
            @Valid @RequestBody List<RecipeRequestDto> requestDtoList
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                recipeService.addRecipe(requestDtoList)
        );
    }

    @PutMapping("/{recipeId}")
    @Operation(summary = "Id에 해당하는 레시피 정보 업데이트")
    public ResponseEntity<?> updateRecipe(
            @PathVariable(value = "recipeId") Long recipeId,
            @Valid @RequestBody RecipeRequestDto dto
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(recipeService.modifyRecipe(recipeId, dto)
        );
    }

    @DeleteMapping("/{recipeId}")
    @Operation(summary = "Id에 해당하는 레시피 삭제(단건)")
    public ResponseEntity<?> deleteRecipe(
            @PathVariable(value = "recipeId") Long recipeId
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseMessage.removeMessage(
                        recipeService.removeRecipe(recipeId))
                );
    }
}