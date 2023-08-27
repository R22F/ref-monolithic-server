package com.example.refmonolithicserver.controller;

import com.example.refmonolithicserver.dto.SettlementDto.SettlementRequestDto;
import com.example.refmonolithicserver.service.ManagementService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ManagementController {

    private final ManagementService managementService;

    @GetMapping("/settlement")
    @Operation(summary = "Retrieve menus")
    public ResponseEntity<?> receiveSettlementInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(managementService.getSettlementInfo());
    }

    @PostMapping("/settlement")
    @Operation(summary = "Send settlement information")
    public ResponseEntity<?> sendSettlementInfo(@RequestBody SettlementRequestDto dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(managementService.setSettlementInfo(dto));
    }
}
