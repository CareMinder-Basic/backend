package com.careminder.backend.dto.account;

public record StaffLoginRequest(
        String loginId,
        String password
) {
}