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
        })
public class Food {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;
    @Column(length = 128) private String name;
    @Column private Integer fixedPrice; // 판매 금액
    @Column(name = "username") private String username;
}
