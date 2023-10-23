package com.example.refmonolithicserver.settlement.dto;

import com.example.refmonolithicserver.settlement.domain.SalesHistory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record SettlementDto() {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SettlementRequestDto{
        @NotEmpty(message = "foods : not null") private List<FoodInfo> foods;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SettlementResponseDto{
        private Integer sum;
        private List<FoodInfo> foods;

        public SettlementResponseDto(List<SalesHistory> salesHistories){

            List<FoodInfo> foodInfos = new ArrayList<>();
            int sum = 0;
            for (SalesHistory salesHistory:salesHistories){
                foodInfos.add(toDto(salesHistory));
                sum+= salesHistory.getTotalPrice();
            }
            this.sum = sum;
            this.foods = foodInfos;
        }

        public FoodInfo toDto(SalesHistory salesHistory){
            return new FoodInfo(
                    salesHistory.getFoodId(),
                    salesHistory.getFoodName(),
                    salesHistory.getFixedPrice(),
                    salesHistory.getCount()
            );
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FoodInfo{
        @NotNull(message = "foodId : not null") private Long foodId;
        @NotBlank(message = "name : not null") private String name;
        @NotNull(message = "fixedPrice : not null") private Integer fixedPrice;
        @NotNull(message = "count : not null") private Integer count;

        public SalesHistory toEntity(String username){
            return SalesHistory.builder()
                    .count(this.count)
                    .fixedPrice(this.fixedPrice)
                    .foodId(this.foodId)
                    .foodName(this.name)
                    .salesDate(LocalDate.now())
                    .username(username)
                    .build();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SalesRequestDto{
        @NotNull(message = "id : not null") private Long id;
        @NotNull(message = "count : not null") private Integer count;
        @NotNull(message = "fixedPrice : not null") private Integer fixedPrice;
        @NotNull(message = "foodId : not null") private Long foodId;
        @NotBlank(message = "foodName : not null") private String foodName;
        @NotNull(message = "salesDate : not null") private LocalDate salesDate;

        public SalesHistory toEntity(String username){
            return SalesHistory.builder()
                    .id(this.id)
                    .count(this.count)
                    .fixedPrice(this.fixedPrice)
                    .foodId(this.foodId)
                    .foodName(this.foodName)
                    .salesDate(this.salesDate)
                    .username(username)
                    .build();
        }
    }
}
