package com.careminder.backend.dto.chat.chat_message;

import com.careminder.backend.dto.chat.subscription.SubscriptionRequest;
import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.model.chat.ChatMessage;
import com.careminder.backend.model.chat.MessageType;
import lombok.Builder;

@Builder
public record ChatJoinMessageRequest(
        long patientRequestId
) {

    public ChatMessage toEntity(final CustomUserDetails customUserDetails, final String senderName){
        return ChatMessage.builder()
                .accountId(customUserDetails.getAccountId())
                .role(customUserDetails.getRole())
                .patientRequestId(patientRequestId)
                .type(MessageType.JOIN)
                .senderName(senderName)
                .content(senderName + "님이 입장했습니다.")
                .build();
    }
}
