package com.careminder.backend.dto.chat.chat_message;

import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.model.chat.chat_message.ChatMessage;
import com.careminder.backend.model.chat.chat_message.MessageType;

public record ChatMessageRequest(
        long patientRequestId,
        String content
) {

    public ChatMessage toEntity(final CustomUserDetails customUserDetails, final String senderName){
        return ChatMessage.builder()
                .type(MessageType.CHAT)
                .patientRequestId(patientRequestId)
                .accountId(customUserDetails.getAccountId())
                .role(customUserDetails.getRole())
                .content(content)
                .senderName(senderName)
                .build();
    }
}
