package com.example.refmonolithicserver.controller;

import com.example.refmonolithicserver.dto.SettlementDto.SettlementRequestDto;
import com.example.refmonolithicserver.service.ManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@Tag(name = "ManagementController",description = "SettlementAPI-정산정보")
public class ManagementController {

    private final ManagementService managementService;

    @GetMapping("/settlement")
    @Operation(summary = "정산 조회(테스트)")
    public ResponseEntity<?> receiveSettlementInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(managementService.getSettlementInfo());
    }

    @PostMapping("/settlement")
    @Operation(summary = "정산 요청(테스트)")
    public ResponseEntity<?> sendSettlementInfo(@RequestBody SettlementRequestDto dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(managementService.setSettlementInfo(dto));
    }
}
