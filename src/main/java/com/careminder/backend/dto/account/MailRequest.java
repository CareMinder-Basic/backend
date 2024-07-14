package com.careminder.backend.dto.account;

public record MailRequest(
        String mail,
        String verifyCode
) {
}
