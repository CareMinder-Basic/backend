package com.careminder.backend.dto.chat.chat_message;

import com.careminder.backend.model.chat.ChatMessage;
import lombok.Builder;

@Builder
public record SimpleChatMessageResponse(
        String name,
        String content
) {
    public static SimpleChatMessageResponse from(final ChatMessage chatMessage) {
        return SimpleChatMessageResponse.builder()
                .content(chatMessage.getContent())
                .name(chatMessage.getSenderName())
                .build();
    }
}
