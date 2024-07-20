package com.careminder.backend.dto.account;

import com.careminder.backend.model.account.Staff;
import com.careminder.backend.model.account.StaffRole;
import com.careminder.backend.model.account.Ward;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record StaffSignUpRequest(
        @NotBlank(message = NO_WARD_NAME) String name,
        @NotBlank(message = NO_LOGIN_ID ) String loginId,
        @NotBlank(message = NO_PASSWORD ) String password,
        @NotBlank(message = NO_PHONE_NUMBER ) String phoneNumber,
        String email,
        String nfc,
        String fingerprint,
        StaffRole staffRole
) {
    public static final String NO_LOGIN_ID = "아이디가 없습니다.";
    public static final String NO_PASSWORD = "패스워드가 없습니다.";
    public static final String NO_WARD_NAME = "병동 이름이 없습니다.";
    public static final String NO_PHONE_NUMBER = "핸드폰 번호가 없습니다.";

    public Staff toEntity(final String encodePassword){
        return Staff.builder()
                .name(name)
                .loginId(loginId)
                .password(encodePassword)
                .phoneNumber(phoneNumber)
                .email(email)
                .nfc(nfc)
                .fingerprint(fingerprint)
                .staffRole(staffRole)
                .build();
    }
}
