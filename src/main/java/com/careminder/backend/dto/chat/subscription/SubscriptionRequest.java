package com.careminder.backend.dto.chat.subscription;

import com.careminder.backend.model.chat.Subscription;

public record SubscriptionRequest(
        long roomId,
        long memberId
) {

    public Subscription toEntity(){
        return Subscription.builder()
                .roomId(roomId)
                .memberId(memberId)
                .build();
    }
}
