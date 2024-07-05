package com.careminder.backend.dto.account;

import com.careminder.backend.model.account.Role;
import com.careminder.backend.model.account.Ward;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record WardSignUpRequest(
        @NotBlank(message = NO_WARD_NAME) String wardName,
        @NotBlank(message = NO_LOGIN_ID ) String loginId,
        @NotBlank(message = NO_PASSWORD ) String password,
        String managerName,
        @NotBlank(message = NO_PHONE_NUMBER ) String managerPhoneNumber,
        String managerEmail
) {
    public static final String NO_LOGIN_ID = "아이디가 없습니다.";
    public static final String NO_PASSWORD = "패스워드가 없습니다.";
    public static final String NO_WARD_NAME = "병동 이름이 없습니다.";
    public static final String NO_PHONE_NUMBER = "핸드폰 번호가 없습니다.";

    public Ward toEntity(final String encodePasssword){
        return Ward.builder()
                .wardName(wardName)
                .loginId(loginId)
                .password(encodePasssword)
                .managerName(managerName)
                .managerPhoneNumber(managerPhoneNumber)
                .managerEmail(managerEmail)
                .build();
    }
}
