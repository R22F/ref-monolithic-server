package com.example.refmonolithicserver.repository;

import com.example.refmonolithicserver.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByUserId(Long userId);
}
