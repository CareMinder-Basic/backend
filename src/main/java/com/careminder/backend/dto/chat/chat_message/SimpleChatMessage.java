package com.careminder.backend.dto.chat.chat_message;

import lombok.Builder;
import lombok.Getter;

@Builder
public record SimpleChatMessage(
        String content
) {
}
