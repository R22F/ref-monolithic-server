package com.example.refmonolithicserver.repository;

import com.example.refmonolithicserver.domain.Food;
import com.example.refmonolithicserver.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}

