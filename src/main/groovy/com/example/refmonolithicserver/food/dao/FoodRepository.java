package com.example.refmonolithicserver.food.dao;

import com.example.refmonolithicserver.food.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    /**
     *
     * @param id must not be {@literal null}.
     * @return Retrieve one item
     */
    @Query("select food " +
            "from Food food " +
            "where food.id = :id")
    Optional<Food> findById(@Param("id") Long id);

    /**
     *
     * @param username User info in Security Context
     * @return All foods of user
     */
    @Query("select food " +
            "from Food food " +
            "where food.username = :username " +
            "order by food.name")
    List<Food> findAllByUsername(@Param("username") String username);

    /**
     *
     * @param id must not be {@literal null}.
     */
   void deleteById(Long id);
}
