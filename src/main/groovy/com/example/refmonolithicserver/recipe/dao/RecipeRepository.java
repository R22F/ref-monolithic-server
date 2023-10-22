package com.example.refmonolithicserver.recipe.dao;

import com.example.refmonolithicserver.recipe.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    /**
     *
     * @param id must not be {@literal null}.
     * @return Retrieve all by food
     */
    @Query("select recipe " +
            "from Recipe recipe " +
            "where recipe.foodId = :id")
    List<Recipe> findByFoodId(@Param("id") Long id);

    /**
     *
     * @param id must not be {@literal null}.
     * @return Retrieve one
     */
    @Query("select recipe " +
            "from Recipe recipe " +
            "where recipe.id = :id")
    Optional<Recipe> findById(@Param("id") Long id);

    /**
     *
     * @param foodId Want to delete
     */
    void deleteByFoodId(Long foodId);

    /**
     *
     * @param ingredientId Want to delete
     */
    void deleteByIngredientId(Long ingredientId);

    /**
     *
     * @param ingredientId Want to check
     * @return true / false
     */
    Boolean existsByIngredientId(Long ingredientId);

    List<Recipe> findByIngredientId(Long ingredientId);
}
