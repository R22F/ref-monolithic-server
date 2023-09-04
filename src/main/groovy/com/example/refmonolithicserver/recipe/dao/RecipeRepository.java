package com.example.refmonolithicserver.recipe.dao;

import com.example.refmonolithicserver.recipe.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("select recipe from Recipe recipe where recipe.foodId = :id")
    List<Recipe> findByFoodId(@Param("id") Long id);

    @Query("select recipe from Recipe recipe where recipe.id = :id")
    Optional<Recipe> findById(@Param("id") Long id);

    void deleteByFoodId(Long foodId);
}
