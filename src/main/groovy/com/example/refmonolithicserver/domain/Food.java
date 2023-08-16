package com.example.refmonolithicserver.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Food {
    @Id @Column(name = "food_id")
    private Long id;

    @Column(length = 128)
    private String name;

    @Column
    private Double primePrice;

    @Column
    private Integer fixedPrice;

    @ManyToOne @JoinColumn(name = "user_id")
    private User user;
}
