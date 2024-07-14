package com.careminder.backend.global.error.exception;

import static com.careminder.backend.global.constant.StatusCodeConstant.UNAUTHORIZED;

public class JWTException extends RuntimeException {
    private final String statusCode = UNAUTHORIZED.statusCode();
    private final String message;

    public JWTException(final String message) {
        this.message = message;
    }

    public String statusCode(){
        return statusCode;
    }

    public String message(){
        return message;
    }
}
