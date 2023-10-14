package com.example.refmonolithicserver.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String message;

    public BusinessException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = ExceptionMessage.UNDEFINED_ERROR.getMessage();
    }

    public BusinessException(String msg) {
        this.errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        this.message = msg;
    }

    public BusinessException(ErrorCode errorCode, ExceptionMessage exceptionMessage) {
        this.errorCode = errorCode;
        this.message = exceptionMessage.getMessage();
    }
}