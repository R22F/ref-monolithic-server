package com.example.refmonolithicserver.user.service;

import com.example.refmonolithicserver.common.exception.BusinessException;
import com.example.refmonolithicserver.common.exception.ErrorCode;
import com.example.refmonolithicserver.user.dao.UserRepository;
import com.example.refmonolithicserver.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.refmonolithicserver.common.exception.ExceptionMessage.USER_EMAIL_DUPLICATED;
import static com.example.refmonolithicserver.common.exception.ExceptionMessage.USER_ID_DUPLICATED;
import static com.example.refmonolithicserver.user.dto.UserDto.UserSignUpRequestDto;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public Object signup(UserSignUpRequestDto user) {
        if (userRepository.findByNickname(user.getUserId()).isPresent())
            throw new BusinessException(ErrorCode.DUPLICATE, USER_ID_DUPLICATED);
        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new BusinessException(ErrorCode.DUPLICATE, USER_EMAIL_DUPLICATED);

        String rawPassword = user.getPassword();

        User userAccount = User.builder()
                .nickname(user.getUserId())
                .birth(user.getBirth())
                .email(user.getEmail())
                .password(encoder.encode(rawPassword))
                .roles("ROLE_USER")
                .build();
        return userRepository.save(userAccount);
    }

    public Object getUserInfo(){
        return userRepository.findAll();
    }
}
