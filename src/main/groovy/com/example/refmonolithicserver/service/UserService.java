package com.example.refmonolithicserver.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.example.refmonolithicserver.dto.UserDto.*;

@Service
public class UserService {
    public UserSignInResponseDto signin(UserSignInRequestDto dto){
        return new UserSignInResponseDto(dto.getUserId());
    }

    public UserSignUpResponseDto signup(UserSignUpRequestDto dto){
        return new UserSignUpResponseDto(1L);
    }

    public UserInfoResponseDto getUserInfo(){
        return new UserInfoResponseDto(
                "USERID","USERNAME", LocalDate.now(), "user@user.com"
        );
    }
}
