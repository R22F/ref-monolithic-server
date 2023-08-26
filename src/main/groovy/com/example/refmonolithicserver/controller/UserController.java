package com.example.refmonolithicserver.controller;

import com.example.refmonolithicserver.common.ResponseMessage;
import com.example.refmonolithicserver.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.refmonolithicserver.dto.UserDto.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ResponseMessage<Object> responseMessage;

    @PostMapping("/signup")
    @Operation(summary = "Join user")
    public ResponseEntity<?> signup(@RequestBody UserSignUpRequestDto user) throws Exception {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseMessage.signinMessage(
                        userService.signup(user).getId())
                );
    }

    @PostMapping("/signin")
    @Operation(summary = "Login")
    public ResponseEntity<?> signin(@RequestBody UserSignInRequestDto user) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseMessage.signupMessage(
                        userService.signin(user).getUserId())
                );
    }

    @GetMapping("/user")
    @Operation(summary = "Get userInfo")
    public ResponseEntity<?> showMyInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUserInfo());
    }
}
