package com.example.refmonolithicserver.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class User{
    @Id @Column(name = "user_id")
    private Long id;
    @Column
    private String nickname;

    @Column
    private String password;

    @Column
    private LocalDate birth;

    @Column
    private String email;

};
