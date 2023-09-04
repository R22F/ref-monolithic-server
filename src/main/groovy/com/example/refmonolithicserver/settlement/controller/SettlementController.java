package com.example.refmonolithicserver.settlement.controller;

import com.example.refmonolithicserver.settlement.dto.SettlementDto.SettlementRequestDto;
import com.example.refmonolithicserver.settlement.service.SettlementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@Tag(name = "SettlementController",description = "SettlementAPI-정산 정보")
public class SettlementController {

    private final SettlementService settlementService;

    @GetMapping("/settlement/{date}")
    @Operation(summary = "정산 조회")
    public ResponseEntity<?> receiveSettlementInfo(
            @PathVariable("date") LocalDate date
            ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(settlementService.get(date));
    }

    @PostMapping("/settlement")
    @Operation(summary = "정산 요청")
    public ResponseEntity<?> sendSettlementInfo(
            @RequestBody SettlementRequestDto dto
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(settlementService.set(dto));
    }

    @PutMapping("/settlement")
    @Operation(summary = "정산 수정")
    public ResponseEntity<?> modifySettlementInfo(
            @RequestBody SettlementRequestDto dto
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(settlementService.update(dto));
    }

    @DeleteMapping("/settlement/{salesId}")
    @Operation(summary = "정산 정보 삭제")
    public ResponseEntity<?> removeSettlementInfo(@PathVariable("salesId") Long salesId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(settlementService.remove(salesId));
    }
}
