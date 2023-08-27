package com.example.refmonolithicserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public record SettlementDto() {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SettlementRequestDto{
        private Total total;
        private LocalDateTime dateTime;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SettlementResponseDto{
        private Total total;
        private LocalDateTime dateTime;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Total{
        private Menus menus;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Menus{
        private List<Menu> menus;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Menu{
        private String menuName;
        private Integer count;
    }
}
