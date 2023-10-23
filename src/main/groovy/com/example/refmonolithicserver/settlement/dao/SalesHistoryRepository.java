package com.example.refmonolithicserver.settlement.dao;

import com.example.refmonolithicserver.settlement.domain.SalesHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SalesHistoryRepository extends JpaRepository<SalesHistory, Long> {

    /**
     *
     * @param id must not be {@literal null}.
     * @return Retrieve one
     */
    Optional<SalesHistory> findById(Long id);

    /**
     *
     * @param date date must not be {@literal null}.
     * @return Retrieve all by corresponding date
     */
    @Query("select salesHistory " +
            "from SalesHistory salesHistory " +
            "where salesHistory.salesDate = :date " +
            "and salesHistory.username = :user")
    List<SalesHistory> findAllBySalesDate(
            @Param("date") LocalDate date,
            @Param("user") String user);

    @Query("select salesHistory " +
            "from SalesHistory salesHistory " +
            "where salesHistory.salesDate " +
            "   between :startDate and :endDate " +
            "and salesHistory.username = :user")
    List<SalesHistory> findAllBySalesDate(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("user") String user
    );

}
