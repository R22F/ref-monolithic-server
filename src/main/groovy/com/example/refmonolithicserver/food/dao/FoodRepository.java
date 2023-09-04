package com.example.refmonolithicserver.food.dao;

import com.example.refmonolithicserver.food.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query("select food from Food food where food.id = :id")
    Optional<Food> findById(@Param("id") Long id);
    @Query("select food from Food food where food.userId = :userId")
    List<Food> findAllByUserId(@Param("userId") Long userId);
}
