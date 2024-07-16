package com.careminder.backend.dto.chat.subscription;

import com.careminder.backend.model.chat.Subscription;

public record SubscriptionRequest(
        Long roomId
) {

    public Subscription toEntity(final Long accountMappingId){
        return Subscription.builder()
                .roomId(roomId)
                .accountMappingId(accountMappingId)
                .build();
    }
}
