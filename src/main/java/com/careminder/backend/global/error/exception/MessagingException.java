package com.careminder.backend.global.error.exception;

import static com.careminder.backend.global.constant.StatusCodeConstant.UNAUTHORIZED;

public class MessagingException extends RuntimeException{
    private final String statusCode = UNAUTHORIZED.statusCode();
    private final String message;

    public MessagingException(final String message) {
        this.message = message;
    }
}
