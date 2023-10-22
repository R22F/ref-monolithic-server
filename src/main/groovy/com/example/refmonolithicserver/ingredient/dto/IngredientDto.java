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
        @NotNull(message = "unitQuantity : not null") private Integer unitQuantity; // 단위 수량
        @NotNull(message = "unitPrice : not null") private Integer unitPrice; // 단위 수량당 가격
        @NotBlank(message = "units : not null") private String units; // 단위
        // 따라서 300g당 3000원이라 하면 -> unitQuantity(units) 는 unitPrice, unitPrice / unitQuantity = primePrice
        @NotNull(message = "remainQuantity : not null") private Integer remainQuantity;
        @NotNull(message = "buyDate : not null") private LocalDate buyDate;
        @NotNull(message = "expiredPeriod : not null") private Integer expiredPeriod;
        @NotNull(message = "alertQuantity : not null") private Integer alertQuantity;
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
                    .primePrice((double) (this.unitPrice/this.unitQuantity))
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
                    .primePrice((double) (this.unitPrice/this.unitQuantity))
                    .unitPrice(this.unitPrice)
                    .units(this.units)
                    .relievedQuantity(this.relievedQuantity)
                    .url(this.url)
                    .username(username)
                    .build();
        }
    }
}
