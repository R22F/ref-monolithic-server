package com.example.refmonolithicserver.user.controller;

import com.example.refmonolithicserver.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.example.refmonolithicserver.user.dto.UserDto.UserSignUpRequestDto;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@Tag(name = "UserController",description = "UserAPI-유저정보")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입")
    public ResponseEntity<?> signup(
            @RequestBody UserSignUpRequestDto user
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.signup(user));
    }

    @GetMapping("/user")
    @Operation(summary = "유저 정보 조회")
    public ResponseEntity<?> showMyInfo(
            Principal principal
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUserInfo(principal.getName()));
    }

    @GetMapping("/check/user/{username}")
    @Operation(summary = "유저 이름 중복 체크")
    public ResponseEntity<?> usernameDuplicateCheck(
            @PathVariable("username") String username
            ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(!userService.checkUser(username));
    }

    @GetMapping("/check/email/{email}")
    @Operation(summary = "이메일 중복 체크")
    public ResponseEntity<?> emailDuplicateCheck(
            @PathVariable("email") String email
            ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(!userService.checkEmail(email));
    }
}
