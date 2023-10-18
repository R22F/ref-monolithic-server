package com.example.refmonolithicserver.common.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private final String timestamp = String.valueOf(LocalDateTime.now());
    private final Integer status;
    private final String error;
    private final String message;

    @Builder
    public ErrorResponse(Integer status, String error, String message){
        this.status = status;
        this.error = error;
        this.message = message;
    }
    
    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode, String message){
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .error(errorCode.getHttpStatus().name())
                        .status(errorCode.getHttpStatus().value())
                        .message(message)
                        .build()
                );

    }
}