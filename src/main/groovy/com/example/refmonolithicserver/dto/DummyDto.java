package com.example.refmonolithicserver.dto;

import java.time.LocalDate;
public record DummyDto(
        Long id,
        String password,
        String checkPassword,
        String name,
        LocalDate birth,
        String nickname,
        String email
) {
}
