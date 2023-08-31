package com.example.refmonolithicserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public record UserDto() {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class UserSignUpRequestDto{
        private String userId;
        private String password;
        private String name;
        private LocalDate birth;
        private String email;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class UserSignInRequestDto{
        private String userId;
        private String password;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class UserInfoResponseDto{
        private String userId;
        private String name;
        private LocalDate birth;
        private String email;
    }
}
