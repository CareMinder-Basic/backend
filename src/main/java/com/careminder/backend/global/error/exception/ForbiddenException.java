package com.careminder.backend.global.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.careminder.backend.global.constant.StatusCodeConstant.FORBIDDEN;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
public class ForbiddenException extends RuntimeException {

    private final String statusCode = FORBIDDEN.statusCode();
    private final String message;

    public ForbiddenException(final String message) {
        this.message = message;
    }
}
