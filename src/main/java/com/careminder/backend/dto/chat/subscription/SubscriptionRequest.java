package com.careminder.backend.dto.chat.subscription;

import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.model.chat.subscription.Subscription;

public record SubscriptionRequest(
        Long patientRequestId
) {

    public Subscription toEntity(final CustomUserDetails customUserDetails){
        return Subscription.builder()
                .patientRequestId(patientRequestId)
                .accountId(customUserDetails.getAccountId())
                .role(customUserDetails.getRole())
                .build();
    }
}
