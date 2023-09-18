package com.example.refmonolithicserver.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public record UserDto() {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class UserSignUpRequestDto{
        private String username;
        private String password;
        private String name;
        private LocalDate birth;
        private String email;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class UserSignInRequestDto{
        private String username;
        private String password;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class UserInfoResponseDto{
        private String username;
        private String name;
        private LocalDate birth;
        private String email;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class UsernameDto{
        private String username;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class UserEmailDto{
        private String email;
    }
}
