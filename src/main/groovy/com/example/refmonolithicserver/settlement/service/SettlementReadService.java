package com.example.refmonolithicserver.settlement.service;

import com.example.refmonolithicserver.settlement.dao.SalesHistoryRepository;
import com.example.refmonolithicserver.settlement.domain.SalesHistory;
import com.example.refmonolithicserver.settlement.dto.SettlementDto.SettlementResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SettlementReadService {

    private final SalesHistoryRepository salesHistoryRepository;

    public SettlementResponseDto getHistory(LocalDate date, String user) {

        List<SalesHistory> salesHistories = salesHistoryRepository.findAllBySalesDate(date, user);
        return new SettlementResponseDto(salesHistories);
    }

    public SettlementResponseDto getHistory(LocalDate startDate, LocalDate endDate, String user) {

        List<SalesHistory> salesHistories = salesHistoryRepository.findAllBySalesDate(startDate, endDate, user);
        return new SettlementResponseDto(salesHistories);
    }
}
