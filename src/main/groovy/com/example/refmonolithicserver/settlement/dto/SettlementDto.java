package com.example.refmonolithicserver.settlement.dto;

import com.example.refmonolithicserver.settlement.domain.SalesHistory;
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
        private Integer sum;
        private List<FoodInfo> foods;
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
        private Long foodId;
        private String name;
        private Integer fixedPrice;
        private Integer count;

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
        private Long id;
        private Integer count;
        private Integer fixedPrice;
        private Long foodId;
        private String foodName;
        private LocalDate salesDate;

        public SalesHistory toEntity(String  username){
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
