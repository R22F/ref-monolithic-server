package com.example.refmonolithicserver.food.controller;

import com.example.refmonolithicserver.food.dto.FoodDto.FoodRequestDto;
import com.example.refmonolithicserver.food.service.FoodWriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/food")
@Tag(name = "FoodWriteController",description = "FoodAPI-판매 가능한 음식과 관련된 조작")
public class FoodWriteController {

    private final FoodWriteService foodService;

    @PostMapping
    @Operation(summary = "새로운 음식정보 생성")
    public ResponseEntity<?> CreateItem(
            @RequestBody FoodRequestDto dto,
            Principal principal
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(foodService.addItem(dto, principal.getName()));
    }

    @PutMapping("/{foodId}")
    @Operation(summary = "Id에 해당하는 음식 정보 업데이트")
    public ResponseEntity<?> updateItem(
            @PathVariable("foodId") Long id,
            @RequestBody FoodRequestDto dto,
            Principal principal
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(foodService.modifyItem(id, dto, principal.getName()));
    }

//    @DeleteMapping("/{foodId}")
//    @Operation(summary = "Id에 해당하는 음식 삭제")
//    public ResponseEntity<?> deleteItem(
//            @PathVariable("foodId") Long id
//    ){
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(foodService.removeItem(id));
//    }

    @DeleteMapping("/{foodId}/recipes")
    @Operation(summary = "FoodId에 해당하는 레시피 삭제(전체)")
    public ResponseEntity<?> deleteRecipeByFoodId(
            @PathVariable(value = "foodId") Long foodId
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(foodService.removeRecipeByFood(foodId));
    }
}
