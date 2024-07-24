package com.careminder.backend.dto.chat.chat_message;

import com.careminder.backend.model.account.constant.Role;
import com.careminder.backend.model.chat.chat_message.ChatMessage;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SimpleChatMessageResponse(
        String senderName,
        String content,
        Role role,
        LocalDateTime createdAt
) {
    public static SimpleChatMessageResponse from(final ChatMessage chatMessage) {
        return SimpleChatMessageResponse.builder()
                .content(chatMessage.getContent())
                .senderName(chatMessage.getSenderName())
                .role(chatMessage.getRole())
                .createdAt(chatMessage.getCreatedAt())
                .build();
    }
}
