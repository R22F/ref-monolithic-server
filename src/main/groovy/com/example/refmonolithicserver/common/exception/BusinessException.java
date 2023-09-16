package com.example.refmonolithicserver.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
    private String message;

    public BusinessException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, ExceptionMessage exceptionMessage) {
        this.errorCode = errorCode;
        this.message = exceptionMessage.getMessage();
    }
}