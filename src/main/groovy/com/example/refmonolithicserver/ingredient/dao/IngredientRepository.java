package com.example.refmonolithicserver.ingredient.dao;

import com.example.refmonolithicserver.ingredient.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Query("select ingredient from Ingredient ingredient where ingredient.id = :id")
    Optional<Ingredient> findById(@Param("id") Long id);
    @Query("select ingredient from Ingredient ingredient where ingredient.userId = :userId")
    List<Ingredient> findAllByUserId(@Param("userId") Long userId);
}
