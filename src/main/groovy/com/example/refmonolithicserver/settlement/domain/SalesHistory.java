package com.example.refmonolithicserver.settlement.domain;

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
@Table(name = "sales_history",
        indexes = {
            @Index(name = "idx_sales_date", columnList = "sales_date"),
            @Index(name = "idx_food_id", columnList = "food_id"),
            @Index(name = "idx_user_id", columnList = "user_id")
        })
public class SalesHistory{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;
    @Column(name = "sales_date") private LocalDate salesDate;

    // FoodID에 대해 부분 함수 종속관계를 가짐
    // 2정규형을 만족하지 못하지만 변하는 Food 의 특정 시간대의 데이터를 가져야 하고
    // 정산이라는 데이터가 자주 갱신이 일어나는 정보가 아니기 때문에 중복데이터를 허용함
    @Column private String foodName;
    @Column private Integer fixedPrice;
    @Column private Integer count;
    @Column(name = "food_id") private Long foodId;
    // 여기까지 foodId에 대한 부분 함수 종속

    @Column(name = "user_id") private Long userId;

    public Integer getTotalPrice(){
        return this.fixedPrice * this.count;
    }
}