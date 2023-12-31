package com.example.refmonolithicserver.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),

    // 400 Bad Request
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST),
    ILLEGAL_STATE(HttpStatus.BAD_REQUEST),
    ACCOUNT_MISMATCH(HttpStatus.BAD_REQUEST),

    // 401 Unauthorized
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED),
    CODE_MISMATCH(HttpStatus.UNAUTHORIZED),

    // 403 Forbidden
    FORBIDDEN(HttpStatus.FORBIDDEN),

    // 404 Not Found
    NOT_FOUND(HttpStatus.NOT_FOUND),

    // 409 Conflict
    DUPLICATE(HttpStatus.CONFLICT),
    DUPLICATE_PASSWORD(HttpStatus.CONFLICT);

    private final HttpStatus httpStatus;

    ErrorCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}