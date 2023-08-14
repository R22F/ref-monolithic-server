package com.example.refmonolithicserver.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class SalesHistory{
    @Id @Column(name = "sales_history_id")
    private Long id;

    @Column private LocalDate salesData;

    @ManyToOne @JoinColumn(name = "food_id")
    private Food food;
}