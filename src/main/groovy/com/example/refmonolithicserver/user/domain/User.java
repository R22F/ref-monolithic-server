package com.example.refmonolithicserver.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user",
        indexes = {
                @Index(name = "idx_nickname", columnList = "nickname"),
                @Index(name = "idx_email", columnList = "email"),
        })
public class User{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;
    @Column(name = "nickname", length = 128) private String nickname;
    @Column(length = 256) @JsonIgnore private String password;
    @Column private LocalDate birth;
    @Column(name = "email", length = 128) private String email;
    @Column @JsonIgnore private String roles;

    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }
};
