package com.example.refmonolithicserver.ingredient.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ingredient",
        indexes = {
                @Index(name = "idx_user", columnList = "username"),
        })
public class Ingredient{
    @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 128)
    private String name;

    @Column(name = "remain_quantity")
    private Integer remainQuantity;

    @Column(name = "unit_quantity")
    private Integer unitQuantity;

    @Column(name = "buy_date")
    private LocalDate buyDate;

    @Column(name = "expired_date")
    private LocalDate expiredDate;

    @Column(name = "alert_quantity")
    private Integer alertQuantity;

    @Column(name = "prime_price")
    private Double primePrice;

    @Column(name = "unit_price")
    private Integer unitPrice;

    @Column(name = "units", length = 32)
    private String units;

    @Column(name = "relieved_quantity")
    private Integer relievedQuantity;

    @Column(name = "url", length = 512)
    private String url;

    @Column(name = "username", length = 128)
    private String username;

    public Ingredient modifyQuantity(int fixedQuantity){
        this.remainQuantity = fixedQuantity;
        if (this.remainQuantity <= alertQuantity){
            log.warn("RemainQuantity is lower than AlertQuantity");
        }
        return this;
    }
};
