package com.example.refmonolithicserver.food.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food",
        indexes = {
                @Index(name = "idx_user", columnList = "username"),
        },
        uniqueConstraints = { @UniqueConstraint(name = "UniqueName", columnNames = { "name" })}
)
public class Food {
    @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 128)
    private String name;

    @Column(name = "fixed_price")
    private Integer fixedPrice; // 판매 금액

    @Column(name = "username", length = 128)
    private String username;
}
