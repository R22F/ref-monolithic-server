package com.example.refmonolithicserver.repository;

import com.example.refmonolithicserver.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
