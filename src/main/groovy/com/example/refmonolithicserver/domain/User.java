package com.example.refmonolithicserver.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class User{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;
    @Column(length = 128) private String nickname;
    @Column(length = 256) private String password;
    @Column(length = 128) private String name;
    @Column private LocalDate birth;
    @Column(length = 128) private String email;
};
