package com.careminder.backend.global.constant.exception;

public enum AccountMappingExceptionConstant {

    ACCOUNT_MAPPING_NOT_FOUND_ERROR("AccountMapping이 존재하지 않습니다.");

    private final String message;

    AccountMappingExceptionConstant(final String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
