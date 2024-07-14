package com.careminder.backend.global.constant.exception;

public enum StaffExceptionConstant {

    STAFF_NOT_FOUND_ERROR("회원이 존재하지 않습니다.");

    private final String message;

    StaffExceptionConstant(final String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
