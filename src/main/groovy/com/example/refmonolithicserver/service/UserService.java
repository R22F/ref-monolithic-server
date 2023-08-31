package com.example.refmonolithicserver.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.example.refmonolithicserver.dto.UserDto.*;

@Service
@Transactional
public class UserService {
    public Long signin(UserSignInRequestDto dto){
        return 1L;
    }

    public Long signup(UserSignUpRequestDto dto){
        return 1L;
    }

    public UserInfoResponseDto getUserInfo(){
        return new UserInfoResponseDto(
                "USERID","USERNAME", LocalDate.now(), "user@user.com"
        );
    }
}
