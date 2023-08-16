package com.example.refmonolithicserver.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class IngredientHistory{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_history_id")
    private Long id;

    @Column
    private Integer remainQuantity;

    @Column
    private LocalDate buyDate;

    @Column
    private LocalDate expiredDate;

    @Column
    private Integer alertQuantity;

    @Column
    private Integer relivedQuantity;

    @Column
    private Integer primeQuantity;

    @Column
    private Integer primePrice;

    @Column
    private Integer expiredPeriod;

    @Column(length = 256)
    private String purchaseUrl;

    @ManyToOne @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
};