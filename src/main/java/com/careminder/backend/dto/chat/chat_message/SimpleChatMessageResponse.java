package com.careminder.backend.dto.chat.chat_message;

import com.careminder.backend.model.chat.chat_message.ChatMessage;
import lombok.Builder;

@Builder
public record SimpleChatMessageResponse(
        String senderName,
        String content
) {
    public static SimpleChatMessageResponse from(final ChatMessage chatMessage) {
        return SimpleChatMessageResponse.builder()
                .content(chatMessage.getContent())
                .senderName(chatMessage.getSenderName())
                .build();
    }
}
