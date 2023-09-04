package com.example.refmonolithicserver.settlement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FoodInfo{
        private Long foodId;
        private String name;
        private Integer fixedPrice;
        private Integer count;
    }
}
