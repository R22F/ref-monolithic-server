package com.example.refmonolithicserver.controller;

import com.example.refmonolithicserver.common.ResponseMessage;
import com.example.refmonolithicserver.dto.FoodDto.FoodRequestDto;
import com.example.refmonolithicserver.service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/food")
@Tag(name = "FoodController",description = "FoodAPI-판매 가능한 음식")
public class FoodController {

    private final FoodService foodService;
    private final ResponseMessage responseMessage;

    @GetMapping("/")
    @Operation(summary = "전체 음식 종류를 출력")
    public ResponseEntity<?> retrieveAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(foodService.getAll());
    }

    @GetMapping("/{foodId}")
    @Operation(summary = "Id로 음식 찾기")
    public ResponseEntity<?> retrieveInventory(
            @PathVariable("foodId") Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(foodService.getItem(id));
    }

    @PostMapping("/")
    @Operation(summary = "새로운 음식정보 생성")
    public ResponseEntity<?> CreateItem(
            @RequestBody FoodRequestDto dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(foodService.addItem(dto));
    }

    @PutMapping("/{foodId}")
    @Operation(summary = "Id에 해당하는 음식 정보 업데이트")
    public ResponseEntity<?> updateItem(
            @PathVariable("foodId") Long id,
            @RequestBody FoodRequestDto dto) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(foodService.modifyItem(id, dto));
    }

    @DeleteMapping("/{foodId}")
    @Operation(summary = "Id에 해당하는 음식 삭제")
    public ResponseEntity<?> deleteItem(@PathVariable("foodId") Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        responseMessage.removeMessage(
                                foodService.removeItem(id))
                );
    }
}
