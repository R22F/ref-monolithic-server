package com.example.refmonolithicserver.settlement.controller;

import com.example.refmonolithicserver.settlement.service.SettlementReadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@Tag(name = "SettlementReadController",description = "SettlementAPI-정산 정보")
public class SettlementReadController {

    private final SettlementReadService settlementService;

    @GetMapping("/settlement/{date}")
    @Operation(summary = "정산 조회")
    public ResponseEntity<?> receiveSettlementInfo(
            @PathVariable("date") LocalDate date
            ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(settlementService.get(date));
    }
}
