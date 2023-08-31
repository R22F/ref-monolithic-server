package com.example.refmonolithicserver.controller;

import com.example.refmonolithicserver.common.ResponseMessage;
import com.example.refmonolithicserver.dto.IngredientDto;
import com.example.refmonolithicserver.dto.IngredientDto.IngredientRequestDto;
import com.example.refmonolithicserver.service.InventoryService;
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
@Tag(name = "InventoryController",description = "IngredientAPI-냉장고에 보관 중인 재료")
public class InventoryController {

    private final InventoryService inventoryService;
    private final ResponseMessage responseMessage;

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

    @PostMapping("/")
    @Operation(summary = "새로운 재고정보 생성")
    public ResponseEntity<?> CreateInventoryItem(@RequestBody IngredientRequestDto dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(inventoryService.addItem(dto));
    }

    @PutMapping("/{ingredientId}")
    @Operation(summary = "Id에 해당하는 재고 정보 업데이트")
    public ResponseEntity<?> updateInventoryItem(
            @PathVariable("ingredientId") Long id,
            @RequestBody IngredientRequestDto dto) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryService.modifyItem(id, dto));
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
