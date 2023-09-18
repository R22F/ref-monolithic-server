package com.example.refmonolithicserver.settlement.controller;

import com.example.refmonolithicserver.settlement.dto.SettlementDto.SalesRequestDto;
import com.example.refmonolithicserver.settlement.dto.SettlementDto.SettlementRequestDto;
import com.example.refmonolithicserver.settlement.service.SettlementWriteService;
import com.example.refmonolithicserver.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@Tag(name = "SettlementWriteController",description = "SettlementAPI-정산 정보")
public class SettlementWriteController {

    private final SettlementWriteService settlementService;

    @PostMapping("/settlement")
    @Operation(summary = "정산 요청 - 다중건 요청 및 재료 정보 수정")
    public ResponseEntity<?> sendSettlementInfo(
            @RequestBody SettlementRequestDto dto,
            Principal principal
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(settlementService.set(dto, principal.getName()));
    }

    @PutMapping("/settlement")
    @Operation(summary = "정산 수정 - 단건 요청 및 재료 정보 수정")
    public ResponseEntity<?> modifySettlementInfo(
            @RequestBody SalesRequestDto dto,
            Principal principal
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(settlementService.update(dto, principal.getName()));
    }

    @DeleteMapping("/settlement/{salesId}")
    @Operation(summary = "정산 정보 삭제")
    public ResponseEntity<?> removeSettlementInfo(@PathVariable("salesId") Long salesId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(settlementService.remove(salesId));
    }
}
