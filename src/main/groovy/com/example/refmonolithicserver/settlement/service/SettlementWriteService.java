package com.example.refmonolithicserver.settlement.service;

import com.example.refmonolithicserver.settlement.dao.SalesHistoryRepository;
import com.example.refmonolithicserver.settlement.dto.SettlementDto.FoodInfo;
import com.example.refmonolithicserver.settlement.dto.SettlementDto.SalesRequestDto;
import com.example.refmonolithicserver.settlement.dto.SettlementDto.SettlementRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SettlementWriteService {

    private final SalesHistoryRepository salesHistoryRepository;

    public LocalDate set(SettlementRequestDto dto) {

        LocalDate today = LocalDate.now();

        for (FoodInfo info: dto.getFoods()){
            salesHistoryRepository.save(info.toEntity(1L));
        }
        // ingredient 정보도 수정 되어야 함
        return today;
    }

    public LocalDate update(SalesRequestDto dto) {

        salesHistoryRepository.save(dto.toEntity(1L));

        // ingredient 정보도 수정 되어야 함
        return dto.getSalesDate();
    }

    public Long remove(Long salesId) {

        salesHistoryRepository.deleteById(salesId);

        return salesId;
    }
}
