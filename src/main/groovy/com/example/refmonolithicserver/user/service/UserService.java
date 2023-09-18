package com.example.refmonolithicserver.user.service;

import com.example.refmonolithicserver.common.exception.BusinessException;
import com.example.refmonolithicserver.common.exception.ErrorCode;
import com.example.refmonolithicserver.user.dao.UserRepository;
import com.example.refmonolithicserver.user.domain.User;
import com.example.refmonolithicserver.user.dto.UserDto;
import com.example.refmonolithicserver.user.dto.UserDto.UserEmailDto;
import com.example.refmonolithicserver.user.dto.UserDto.UsernameDto;
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
        if (userRepository.findByUsername(user.getUsername()).isPresent())
            throw new BusinessException(ErrorCode.DUPLICATE, USER_ID_DUPLICATED);
        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new BusinessException(ErrorCode.DUPLICATE, USER_EMAIL_DUPLICATED);

        String rawPassword = user.getPassword();

        User userAccount = User.builder()
                .username(user.getUsername())
                .birth(user.getBirth())
                .email(user.getEmail())
                .password(encoder.encode(rawPassword))
                .roles("ROLE_USER")
                .build();
        return userRepository.save(userAccount);
    }

    public Object getUserInfo(String username){
        return userRepository.findByUsername(username);
    }

    public boolean checkUser(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean checkEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
