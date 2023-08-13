package com.example.refmonolithicserver.repository;

import com.example.refmonolithicserver.domain.IngredientHistory;
import com.example.refmonolithicserver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

