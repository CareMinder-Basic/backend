package com.careminder.backend.dto.account;

import com.careminder.backend.model.account.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record WardSignUpRequest(
        @NotBlank(message = NO_WARD_NAME) String name,
        @NotBlank(message = NO_LOGIN_ID ) String loginId,
        @NotBlank(message = NO_PASSWORD ) String password,
        @NotBlank(message = NO_PHONE_NUMBER ) String phoneNumber
) {
    public static final String NO_LOGIN_ID = "아이디가 없습니다.";
    public static final String NO_PASSWORD = "패스워드가 없습니다.";
    public static final String NO_WARD_NAME = "병동 이름이 없습니다.";
    public static final String NO_PHONE_NUMBER = "핸드폰 번호가 없습니다.";
//    public static final String INVALID_EMAIL_FORMAT = "이메일 형식이 올바르지 않습니다.";
//    public static final String NO_BIRTH_DATE_ERROR = "birthDate 값이 없습니다.";
//    public static final String NO_GENDER_ENUM_ERROR = "GENDER 값이 없습니다.";
}
