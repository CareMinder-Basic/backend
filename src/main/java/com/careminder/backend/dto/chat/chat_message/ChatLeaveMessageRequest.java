package com.careminder.backend.dto.chat.chat_message;

import com.careminder.backend.dto.chat.subscription.UnsubscribeRequest;
import com.careminder.backend.model.chat.ChatMessage;
import com.careminder.backend.model.chat.MessageType;
import lombok.Builder;

@Builder
public record ChatLeaveMessageRequest(
        long roomId
) {

    public ChatMessage toEntity(final Long accountMappingId, final String senderName) {
        return ChatMessage.builder()
                .accountMappingId(accountMappingId)
                .roomId(roomId)
                .type(MessageType.LEAVE)
                .senderName(senderName)
                .content(senderName + "님이 퇴장했습니다.")
                .build();
    }
}