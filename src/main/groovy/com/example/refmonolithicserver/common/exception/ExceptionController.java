package com.example.refmonolithicserver.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<ErrorResponse> exception(BusinessException e){
        return ErrorResponse.toResponseEntity(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ErrorResponse.toResponseEntity(ErrorCode.INVALID_PARAMETER, Arrays.toString(e.getDetailMessageArguments()));
    }
}
