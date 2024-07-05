package com.careminder.backend.global.response;

import lombok.*;

@Builder
public record JWTResponse(
        String accessToken,
        String refreshToken
) {
    public static JWTResponse toResponses(final String accessToken, final String refreshToken) {
        return JWTResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}