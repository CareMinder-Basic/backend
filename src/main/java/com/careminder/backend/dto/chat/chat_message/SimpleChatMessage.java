package com.careminder.backend.dto.chat.chat_message;

import lombok.Builder;

@Builder
public record SimpleChatMessage(
        String content,
        long memberId
) {
}
