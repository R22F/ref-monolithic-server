package com.example.refmonolithicserver.service;

import com.example.refmonolithicserver.dto.SettlementDto;
import com.example.refmonolithicserver.dto.SettlementDto.SettlementRequestDto;
import com.example.refmonolithicserver.dto.SettlementDto.SettlementResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ManagementService {

    public SettlementResponseDto getSettlementInfo() {
        SettlementDto.Menu menu1 = new SettlementDto.Menu("Chicken", 10);
        SettlementDto.Menu menu2 = new SettlementDto.Menu("Pizza", 3);
        SettlementDto.Menu menu3 = new SettlementDto.Menu("Pasta", 8);
        SettlementDto.Menus menus = new SettlementDto.Menus(List.of(menu1, menu2, menu3));
        SettlementDto.Total total = new SettlementDto.Total(menus);
        return new SettlementResponseDto(total, LocalDateTime.now());
    }

    public Object setSettlementInfo(SettlementRequestDto dto) {
        return new SettlementResponseDto(dto.getTotal(), dto.getDateTime());
    }
}
