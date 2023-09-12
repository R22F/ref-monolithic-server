package com.example.refmonolithicserver.settlement.dao;

import com.example.refmonolithicserver.settlement.domain.SalesHistory;
import org.springframework.data.jpa.repository.JpaRepository;

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
    List<SalesHistory> findAllBySalesDate(LocalDate date);
}
