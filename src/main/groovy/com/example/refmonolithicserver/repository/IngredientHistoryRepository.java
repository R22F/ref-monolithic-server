package com.example.refmonolithicserver.repository;

import com.example.refmonolithicserver.domain.IngredientHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientHistoryRepository extends JpaRepository<IngredientHistory, Long> {
}

