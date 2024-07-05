package com.careminder.backend.dto.account;

public record WardLoginRequest(
        String loginId,
        String password
) {
}
