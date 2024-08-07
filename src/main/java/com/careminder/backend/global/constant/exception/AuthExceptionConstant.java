package com.careminder.backend.global.constant.exception;

public enum AuthExceptionConstant {

    MEMBER_NOT_FOUND_ERROR("회원이 존재하지 않습니다."),
    EMAIL_NOT_FOUND_ERROR("이메일이 존재하지 않습니다."),
    ALREADY_EXIST_EMAIL_ERROR("이미 가입된 이메일이 존재합니다."),
    EMAIL_PASSWORD_ERROR("이메일 또는 비밀번호가 올바르지 않습니다. 다시 확인해주세요."),
    PASSWORD_ERROR("비밀번호가 올바르지 않습니다."),
    EMAIL_ALREADY_USED_ERROR("이미 가입한 이메일입니다."),
    HOST_NOT_FOUND_ERROR("모임 생성자를 찾을 수 없습니다.");

    private final String message;

    AuthExceptionConstant(final String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
