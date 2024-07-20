package com.careminder.backend.dto.chat.chat_message;

import com.careminder.backend.model.account.Role;
import com.careminder.backend.model.chat.ChatMessage;
import com.careminder.backend.model.chat.MessageType;

public record ChatMessageRequest(
        long roomId,
        String content
) {

    public ChatMessage toEntity(final Long accountMappingId, final String senderName){
        return ChatMessage.builder()
                .type(MessageType.CHAT)
                .roomId(roomId)
                .accountMappingId(accountMappingId)
                .content(content)
                .senderName(senderName)
                .build();
    }
}
