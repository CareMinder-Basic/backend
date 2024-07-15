package com.careminder.backend.dto.chat.subscription;

public record UnsubscribeRequest(
        long roomId,
        long memberId
) {
}
