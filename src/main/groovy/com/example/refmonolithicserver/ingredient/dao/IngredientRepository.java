package com.example.refmonolithicserver.ingredient.dao;

import com.example.refmonolithicserver.ingredient.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    /**
     *
     * @param id must not be {@literal null}.
     * @return Retrieve One
     */
    @Query("select ingredient from Ingredient ingredient where ingredient.id = :id")
    Optional<Ingredient> findById(@Param("id") Long id);

    /**
     *
     * @param userId User info in Security Context
     * @return All ingredients of user
     */
    @Query("select ingredient from Ingredient ingredient where ingredient.userId = :userId")
    List<Ingredient> findAllByUserId(@Param("userId") Long userId);

    /**
     *
     * @param id must not be {@literal null}.
     */
    @Query("delete from Food where id = :id")
    void deleteById(@Param("id") Long id);
}