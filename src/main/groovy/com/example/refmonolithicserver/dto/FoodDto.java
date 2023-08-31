package com.example.refmonolithicserver.dto;

import com.example.refmonolithicserver.domain.Food;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record FoodDto() {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FoodRequestDto{
        private String name;
        private Integer fixedPrice;

        public Food toEntity(Long userId){
            return Food.builder()
                    .name(this.name)
                    .fixedPrice(this.fixedPrice)
                    .userId(userId)
                    .build();
        }
        public Food toEntity(Long userId, Long id){
            return Food.builder()
                    .id(id)
                    .name(this.name)
                    .fixedPrice(this.fixedPrice)
                    .userId(userId)
                    .build();
        }
    }
}
