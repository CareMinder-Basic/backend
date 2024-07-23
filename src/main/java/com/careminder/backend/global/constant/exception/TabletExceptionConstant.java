package com.careminder.backend.global.constant.exception;

public enum TabletExceptionConstant {
    TABLET_NOT_FOUND_ERROR("태블릿이 존재하지 않습니다.");

    private final String message;

    TabletExceptionConstant(final String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
