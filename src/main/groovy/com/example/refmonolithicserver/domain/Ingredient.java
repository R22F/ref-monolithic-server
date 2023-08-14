package com.example.refmonolithicserver.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Ingredient{
    @Id @Column(name = "ingredient_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String units; // 단위

    @ManyToOne@JoinColumn(name = "user_id")
    private User user;
};