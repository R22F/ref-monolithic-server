package com.example.refmonolithicserver.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class SalesHistory{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_history_id")
    private Long id;

    @Column private LocalDate salesDate;

    @ManyToOne @JoinColumn(name = "food_id")
    private Food food;
}