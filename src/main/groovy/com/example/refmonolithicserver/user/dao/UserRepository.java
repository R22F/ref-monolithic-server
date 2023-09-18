package com.example.refmonolithicserver.user.dao;

import com.example.refmonolithicserver.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     *
     * @param name username
     * @return user info
     */
    @Query("select user from User user where user.username=:username")
    Optional<User> findByUsername(@Param("username") String name);

    /**
     *
     * @param email user email
     * @return user info
     */
    @Query("select user from User user where user.email=:email")
    Optional<User> findByEmail(@Param("email") String email);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
