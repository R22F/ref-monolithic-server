package com.example.refmonolithicserver.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public record UserDto() {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class UserSignUpRequestDto{
        @NotBlank(message = "username : not null") private String username;
        @NotBlank(message = "password : not null") private String password;
        @NotBlank(message = "name : not null") private String name;
        @NotNull(message = "birth : not null") private LocalDate birth;
        @NotBlank(message = "email : not null") private String email;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class UserSignInRequestDto{
        @NotBlank private String username;
        @NotBlank private String password;
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
