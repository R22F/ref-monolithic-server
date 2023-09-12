package com.example.refmonolithicserver.ingredient.controller;

import com.example.refmonolithicserver.ingredient.service.InventoryReadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
@Tag(name = "InventoryReadController",description = "IngredientAPI-냉장고에 보관 중인 재료")
public class InventoryReadController {

    private final InventoryReadService inventoryService;

    @GetMapping("/")
    @Operation(summary = "모든 재고 정보 출력")
    public ResponseEntity<?> retrieveAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryService.getAll());
    }

    @GetMapping("/{ingredientId}")
    @Operation(summary = "Id에 해당하는 재고 정보 출력")
    public ResponseEntity<?> retrieveInventory(@PathVariable("ingredientId") Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryService.getItem(id));
    }
}
