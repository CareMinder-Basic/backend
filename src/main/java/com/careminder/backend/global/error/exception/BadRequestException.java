package com.careminder.backend.global.error.exception;

import lombok.Getter;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
public class BadRequestException extends RuntimeException {

    private final String statusCode = String.valueOf(BAD_REQUEST.value());
    private final String message;

    public BadRequestException(final String message) {
        this.message = message;
    }
}
