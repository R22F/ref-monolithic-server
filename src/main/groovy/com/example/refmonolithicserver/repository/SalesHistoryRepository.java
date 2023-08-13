package com.example.refmonolithicserver.repository;

import com.example.refmonolithicserver.domain.IngredientHistory;
import com.example.refmonolithicserver.domain.SalesHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesHistoryRepository extends JpaRepository<SalesHistory, Long> {
}

