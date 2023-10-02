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
        private Integer unitQuantity;
        private Integer remainQuantity;
        private LocalDate buyDate;
        private Integer expiredPeriod;
        private Integer alertQuantity;
        private Integer unitPrice;
        private Double primePrice;
        private String units;
        private Integer relievedQuantity;
        private String url;

        public Ingredient toEntity(String username){
            return Ingredient.builder()
                    .name(this.name)
                    .remainQuantity(this.remainQuantity)
                    .unitQuantity(this.unitQuantity)
                    .buyDate(this.buyDate)
                    .expiredDate(this.buyDate.plusDays(this.expiredPeriod))
                    .alertQuantity(this.alertQuantity)
                    .primePrice(this.primePrice)
                    .unitPrice(this.unitPrice)
                    .units(this.units)
                    .relievedQuantity(this.relievedQuantity)
                    .url(this.url)
                    .username(username)
                    .build();
        }

        public Ingredient toEntity(Long id, String username){
            return Ingredient.builder()
                    .id(id)
                    .name(this.name)
                    .remainQuantity(this.remainQuantity)
                    .unitQuantity(this.unitQuantity)
                    .buyDate(this.buyDate)
                    .expiredDate(this.buyDate.plusDays(this.expiredPeriod))
                    .alertQuantity(this.alertQuantity)
                    .primePrice(this.primePrice)
                    .unitPrice(this.unitPrice)
                    .units(this.units)
                    .relievedQuantity(this.relievedQuantity)
                    .url(this.url)
                    .username(username)
                    .build();
        }
    }
}
