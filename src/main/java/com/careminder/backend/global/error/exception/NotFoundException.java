package com.careminder.backend.global.error.exception;

import lombok.Getter;

import static com.careminder.backend.global.constant.StatusCodeConstant.NOT_FOUND;

@Getter
public class NotFoundException extends RuntimeException{
    private final String statusCode = NOT_FOUND.statusCode();
    private final String message;

    public NotFoundException(final String message) {
        this.message = message;
    }
}