package com.example.refmonolithicserver.ingredient.controller;

import com.example.refmonolithicserver.common.ResponseMessage;
import com.example.refmonolithicserver.ingredient.dto.IngredientDto.IngredientRequestDto;
import com.example.refmonolithicserver.ingredient.service.InventoryWriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
@Tag(name = "InventoryWriteController",description = "IngredientAPI-냉장고에 보관 중인 재료")
public class InventoryWriteController {

    private final InventoryWriteService inventoryService;
    private final ResponseMessage responseMessage;

    @PostMapping("/")
    @Operation(summary = "새로운 재고정보 생성")
    public ResponseEntity<?> CreateInventoryItem(
            @Valid @RequestBody IngredientRequestDto dto,
            Principal principal){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(inventoryService.addItem(dto, principal.getName()));
    }

    @PutMapping("/{ingredientId}")
    @Operation(summary = "Id에 해당하는 재고 정보 업데이트")
    public ResponseEntity<?> updateInventoryItem(
            @PathVariable("ingredientId") Long id,
            @Valid @RequestBody IngredientRequestDto dto,
            Principal principal) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryService.modifyItem(id, dto, principal.getName()));
    }

    @DeleteMapping("/{ingredientId}")
    @Operation(summary = "Id에 해당하는 재고 삭제")
    public ResponseEntity<?> deleteInventoryItem(@PathVariable("ingredientId") Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        responseMessage.removeMessage(inventoryService.removeItem(id))
                );
    }
}
