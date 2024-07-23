package com.careminder.backend.dto.chat.chat_message;

import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.model.account.Role;
import com.careminder.backend.model.chat.ChatMessage;
import com.careminder.backend.model.chat.MessageType;

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
