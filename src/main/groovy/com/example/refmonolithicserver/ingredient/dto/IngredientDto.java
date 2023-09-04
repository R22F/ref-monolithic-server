package com.example.refmonolithicserver.ingredient.dto;

import com.example.refmonolithicserver.ingredient.domain.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public record IngredientDto() {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IngredientRequestDto {
        private String name;
        private Integer buyQuantity;
        private Integer remainQuantity;
        private LocalDate buyDate;
        private Integer expiredPeriod;
        private Integer alertQuantity;
        private Double primePrice;
        private String units;
        private Integer relievedQuantity;
        private String url;

        public Ingredient toEntity(){
            return Ingredient.builder()
                    .name(this.name)
                    .remainQuantity(this.remainQuantity)
                    .buyDate(this.buyDate)
                    .expiredDate(this.buyDate.plusDays(this.expiredPeriod))
                    .alertQuantity(this.alertQuantity)
                    .primePrice(this.primePrice)
                    .units(this.units)
                    .relievedQuantity(this.relievedQuantity)
                    .url(this.url)
                    .userId(1L)
                    .build();
        }

        public Ingredient toEntity(Long id){
            return Ingredient.builder()
                    .id(id)
                    .name(this.name)
                    .remainQuantity(this.remainQuantity)
                    .buyDate(this.buyDate)
                    .expiredDate(this.buyDate.plusDays(this.expiredPeriod))
                    .alertQuantity(this.alertQuantity)
                    .primePrice(this.primePrice)
                    .units(this.units)
                    .relievedQuantity(this.relievedQuantity)
                    .url(this.url)
                    .userId(1L)
                    .build();
        }
    }
}
