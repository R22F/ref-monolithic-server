package com.example.refmonolithicserver.repository;

import com.example.refmonolithicserver.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    void deleteByFoodId(Long foodId);
}
