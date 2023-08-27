package com.example.refmonolithicserver.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Ingredient{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String units;
};