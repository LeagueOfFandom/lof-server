package com.lof.lofserver.exception;

import com.lof.lofserver.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> handleBaseEx(UserException exception){
        log.error("UserException errorMessage: "+ exception.getExceptionType().getErrorMessage());
        log.error("UserException errorCode: "+ exception.getExceptionType().getErrorCode());

        return ResponseEntity.status(exception.getExceptionType().getHttpStatus())
                .body(ErrorResponse.builder()
                        .code(String.valueOf(exception.getExceptionType().getErrorCode()))
                        .message(exception.getExceptionType().getErrorMessage())
                        .build());
    }
}
