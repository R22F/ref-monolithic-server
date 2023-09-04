package com.example.refmonolithicserver.ingredient.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;
    @Column private String name;
    @Column private Integer remainQuantity;
    @Column private LocalDate buyDate;
    @Column private LocalDate expiredDate;
    @Column private Integer alertQuantity;
    @Column private Double primePrice;
    @Column private String units;
    @Column private Integer relievedQuantity;
    @Column private String url;
    @Column private Long userId;
};
