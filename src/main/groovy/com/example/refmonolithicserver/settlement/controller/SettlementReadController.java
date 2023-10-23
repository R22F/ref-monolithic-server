package com.example.refmonolithicserver.settlement.controller;

import com.example.refmonolithicserver.settlement.service.SettlementReadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@Tag(name = "SettlementReadController",description = "SettlementAPI-정산 정보")
public class SettlementReadController {

    private final SettlementReadService settlementService;

    @GetMapping("/settlement/{date}")
    @Operation(summary = "정산 조회(해당 날짜 단건)")
    public ResponseEntity<?> receiveSettlementInfo(
            @PathVariable("date") LocalDate date,
            Principal principal
            ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(settlementService.getHistory(date, principal.getName()));
    }

    @GetMapping("/settlement/")
    @Operation(summary = "정산 조회(시작일 부터 종료일 까지)")
    public ResponseEntity<?> receiveSettlementInfoByDate(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate,
            Principal principal
            ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(settlementService.getHistory(startDate, endDate, principal.getName()));
    }
}
