package com.careminder.backend.global.error.advice;

import com.careminder.backend.global.error.exception.BadRequestException;
import com.careminder.backend.global.error.exception.ForbiddenException;
import com.careminder.backend.global.error.exception.InvalidCredentialsException;
import com.careminder.backend.global.error.exception.NotFoundException;
import com.careminder.backend.global.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ApiRestControllerAdvice {

    // 400
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorResponse handleException(final BadRequestException e) {
        log.info("BadRequestException={}", e.getMessage());
        return ErrorResponse.of(e.getStatusCode(), e.getMessage());
    }

    // 401
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidCredentialsException.class)
    public ErrorResponse handleException(final InvalidCredentialsException e) {
        log.info("InvalidCredentialsException={}", e.getMessage());
        return ErrorResponse.of(e.getStatusCode(), e.getMessage());
    }

    // 403
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    public ErrorResponse handleException(final ForbiddenException e) {
        log.info("ForbiddenException Exception={}", e.getMessage());
        return ErrorResponse.of(e.getStatusCode(), e.getMessage());
    }

    // 404
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundException(final NotFoundException e){
        log.info("NotFoundException={}", e.getMessage());
        return ErrorResponse.of(e.getStatusCode(), e.getMessage());
    }

    // Valid Annotation Handling
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationException(final MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();
        log.info("MethodArgumentNotValidException={}", e.getMessage());
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        String errorMessage = String.join(" ", errorMap.values());
        return ErrorResponse.of(String.valueOf(HttpStatus.BAD_REQUEST.value()), errorMessage);
    }
}
