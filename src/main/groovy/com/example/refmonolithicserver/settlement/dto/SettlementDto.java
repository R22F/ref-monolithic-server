package com.example.refmonolithicserver.settlement.dto;

import com.example.refmonolithicserver.settlement.domain.SalesHistory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Embedded;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record SettlementDto() {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SettlementRequestDto{
        @NotEmpty(message = "foods : not null") private List<FoodInfo> foods;
        private LocalDate reqDate;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SettlementResponseDto{
        private Double primePrice;
        private Integer sum;
        private List<FoodInfo> foods;

        public SettlementResponseDto(List<SalesHistory> salesHistories){

            List<FoodInfo> foodInfos = new ArrayList<>();
            int sum = 0;
            double primePrice = 0.0;
            for (SalesHistory salesHistory:salesHistories){
                foodInfos.add(toDto(salesHistory));
                sum+= salesHistory.getTotalFixedPrice();
                primePrice+=salesHistory.getTotalPrimePrice();
            }
            this.sum = sum;
            this.primePrice = primePrice;
            this.foods = foodInfos.stream()
                    .collect(Collectors.groupingBy(FoodInfo::getName))
                    .entrySet()
                    .stream()
                    .map(entry->{
                        List<FoodInfo> groupedList = entry.getValue();
                        int count = groupedList.stream()
                                .mapToInt(FoodInfo::getCount)
                                .sum();
                        FoodInfo foodInfo = groupedList.get(0);
                        foodInfo.setCount(count);
                        return groupedList.get(0);
                    })
                    .collect(Collectors.toList());
        }

        public FoodInfo toDto(SalesHistory salesHistory){
            return new FoodInfo(
                    salesHistory.getFoodId(),
                    salesHistory.getFoodName(),
                    salesHistory.getFixedPrice(),
                    salesHistory.getPrimePrice(),
                    salesHistory.getCount(),
                    salesHistory.getNote()
            );
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FoodInfo{
        private Long id;
        @NotBlank(message = "name : not null") private String name;
        @NotNull(message = "fixedPrice : not null") private Integer fixedPrice;
        @NotNull(message = "primePrice : not null") private Double primePrice;
        @NotNull(message = "count : not null") private Integer count;
        @Embedded.Nullable private String note;

        public SalesHistory toEntity(String username){
            return SalesHistory.builder()
                    .count(this.count)
                    .fixedPrice(this.fixedPrice)
                    .primePrice(this.primePrice)
                    .foodId(this.id)
                    .foodName(this.name)
                    .salesDate(LocalDate.now())
                    .username(username)
                    .note(this.note)
                    .build();
        }
        public SalesHistory toEntity(String username, LocalDate date, Double primePrice){
            return SalesHistory.builder()
                    .count(this.count)
                    .fixedPrice(this.fixedPrice)
                    .primePrice(primePrice)
                    .foodId(this.id)
                    .foodName(this.name)
                    .salesDate(date)
                    .username(username)
                    .note(this.note)
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
        @Embedded.Nullable private String note;

        public SalesHistory toEntity(String username){
            return SalesHistory.builder()
                    .id(this.id)
                    .count(this.count)
                    .fixedPrice(this.fixedPrice)
                    .foodId(this.foodId)
                    .foodName(this.foodName)
                    .salesDate(this.salesDate)
                    .username(username)
                    .note(this.note)
                    .build();
        }
    }
}
