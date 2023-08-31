package com.example.refmonolithicserver.common;

import lombok.Getter;
import java.util.List;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
    private String path;
    private List<String> arguments;

    public BusinessException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String path, List<String> arguments) {
        this.errorCode = errorCode;
        this.path = path;
        this.arguments = arguments;
    }

    public BusinessException(ErrorCode errorCode, String path, List<String> arguments, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.path = path;
        this.arguments = arguments;
    }
}