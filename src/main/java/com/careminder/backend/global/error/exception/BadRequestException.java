package com.careminder.backend.global.error.exception;

import lombok.Getter;

import static com.careminder.backend.global.constant.StatusCodeConstant.BAD_REQUEST;

@Getter
public class BadRequestException extends RuntimeException {

    private final String statusCode = BAD_REQUEST.statusCode();
    private final String message;

    public BadRequestException(final String message) {
        this.message = message;
    }
}
