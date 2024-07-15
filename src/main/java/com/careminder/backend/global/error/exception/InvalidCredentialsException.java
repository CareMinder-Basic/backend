package com.careminder.backend.global.error.exception;

import lombok.Getter;

import static com.careminder.backend.global.constant.StatusCodeConstant.UNAUTHORIZED;

@Getter
public class InvalidCredentialsException extends RuntimeException {

    private final String statusCode = UNAUTHORIZED.statusCode();
    private final String message;

    public InvalidCredentialsException(final String message) {
        this.message = message;
    }
}
