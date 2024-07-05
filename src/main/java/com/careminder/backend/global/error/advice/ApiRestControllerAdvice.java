package com.careminder.backend.global.error.advice;

import com.careminder.backend.global.error.exception.InvalidCredentialsException;
import com.careminder.backend.global.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiRestControllerAdvice {

    // 401
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidCredentialsException.class)
    public ErrorResponse handleException(final InvalidCredentialsException e) {
        log.info("InvalidCredentialsException={}", e.getMessage());
        return ErrorResponse.of(e.getStatusCode(), e.getMessage());
    }
}
