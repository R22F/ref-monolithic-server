package com.example.refmonolithicserver.user.controller;

import com.example.refmonolithicserver.common.ResponseMessage;
import com.example.refmonolithicserver.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.example.refmonolithicserver.user.dto.UserDto.UserSignUpRequestDto;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@Tag(name = "UserController",description = "UserAPI-유저정보")
public class UserController {

    private final UserService userService;
    private final ResponseMessage responseMessage;

    @PostMapping("/signup")
    @Operation(summary = "회원가입")
    public ResponseEntity<?> signup(@RequestBody UserSignUpRequestDto user){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.signup(user));
    }

//    @PostMapping("/signin")
//    @Operation(summary = "로그인(테스트)")
//    public ResponseEntity<?> signin(@RequestBody UserSignInRequestDto user) throws Exception {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(responseMessage.signupMessage(
//                        userService.signin(user))
//                );
//    }

    @GetMapping("/user")
    @Operation(summary = "유저 정보 조회(테스트)")
    public ResponseEntity<?> showMyInfo(
            Principal principal
    ){
        log.info(String.valueOf(principal.getName()));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUserInfo());
    }
}
