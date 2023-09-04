package com.example.refmonolithicserver.settlement.dao;

import com.example.refmonolithicserver.settlement.domain.SalesHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesHistoryRepository extends JpaRepository<SalesHistory, Long> {
}
