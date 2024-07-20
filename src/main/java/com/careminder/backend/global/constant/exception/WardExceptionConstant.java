package com.careminder.backend.global.constant.exception;

public enum WardExceptionConstant {
    WARD_NOT_FOUND_ERROR("병동이 존재하지 않습니다.");

    private final String message;

    WardExceptionConstant(final String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
