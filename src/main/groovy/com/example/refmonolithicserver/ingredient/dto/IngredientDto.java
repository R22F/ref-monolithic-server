package com.example.refmonolithicserver.ingredient.dto;

import com.example.refmonolithicserver.ingredient.domain.Ingredient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public record IngredientDto() {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IngredientRequestDto {
        @NotBlank(message = "name : not null") private String name;
        @NotNull(message = "unitQuantity : not null") private Integer unitQuantity;
        @NotNull(message = "remainQuantity : not null") private Integer remainQuantity;
        @NotNull(message = "buyDate : not null") private LocalDate buyDate;
        @NotNull(message = "expiredPeriod : not null") private Integer expiredPeriod;
        @NotNull(message = "alertQuantity : not null") private Integer alertQuantity;
        @NotNull(message = "unitPrice : not null") private Integer unitPrice;
        @NotNull(message = "primePrice : not null") private Double primePrice;
        @NotBlank(message = "units : not null") private String units;
        @NotNull(message = "relievedQuantity : not null") private Integer relievedQuantity;
        @NotNull(message = "url : not null") private String url;

        public Ingredient toEntity(String username){
            return Ingredient.builder()
                    .name(this.name)
                    .remainQuantity(this.remainQuantity)
                    .unitQuantity(this.unitQuantity)
                    .buyDate(this.buyDate)
                    .expiredDate(this.buyDate.plusDays(this.expiredPeriod))
                    .expiredPeriod(this.expiredPeriod)
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
                    .expiredPeriod(this.expiredPeriod)
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
