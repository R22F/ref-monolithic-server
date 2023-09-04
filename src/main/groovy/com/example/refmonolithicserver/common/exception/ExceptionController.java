package com.example.refmonolithicserver.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<ErrorResponse> exception(BusinessException e){
        return ErrorResponse.toResponseEntity(e.getErrorCode(), e.getMessage());
    }
}
