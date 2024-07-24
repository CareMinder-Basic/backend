package com.careminder.backend.dto.chat.chat_message;

import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.model.chat.chat_message.ChatMessage;
import com.careminder.backend.model.chat.chat_message.MessageType;
import lombok.Builder;

@Builder
public record ChatLeaveMessageRequest(
        long patientRequestId
) {

    public ChatMessage toEntity(final CustomUserDetails customUserDetails, final String senderName) {
        return ChatMessage.builder()
                .accountId(customUserDetails.getAccountId())
                .role(customUserDetails.getRole())
                .patientRequestId(patientRequestId)
                .type(MessageType.LEAVE)
                .senderName(senderName)
                .content(senderName + "님이 퇴장했습니다.")
                .build();
    }
}