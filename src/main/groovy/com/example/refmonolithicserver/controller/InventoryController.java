package com.example.refmonolithicserver.controller;

import com.example.refmonolithicserver.common.ResponseMessage;
import com.example.refmonolithicserver.dto.RecipeDto.IngredientRequestDto;
import com.example.refmonolithicserver.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    private final ResponseMessage<Object> responseMessage;

    @GetMapping("/{ingredientId}")
    @Operation(summary = "Retrieve ingredient by Id")
    public ResponseEntity<?> retrieveInventory(@PathVariable("ingredientId") Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryService.getItem(id));
    }

    @PostMapping("/")
    @Operation(summary = "Create ingredient")
    public ResponseEntity<?> CreateInventoryItem(@RequestBody IngredientRequestDto dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        responseMessage.createMessage(inventoryService.addItem(dto))
                );
    }

    @PutMapping("/{ingredientId}")
    @Operation(summary = "Update ingredient")
    public ResponseEntity<?> updateInventoryItem(@PathVariable("ingredientId") Long id,@RequestBody IngredientRequestDto dto){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryService.modifyItem(id, dto));
    }

    @DeleteMapping("/{ingredientId}")
    @Operation(summary = "Delete ingredient")
    public ResponseEntity<?> deleteInventoryItem(@PathVariable("ingredientId") Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        responseMessage.removeMessage(inventoryService.removeItem(id))
                );
    }
}
