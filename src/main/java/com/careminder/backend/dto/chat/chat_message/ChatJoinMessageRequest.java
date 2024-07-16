package com.careminder.backend.dto.chat.chat_message;

import com.careminder.backend.dto.chat.subscription.SubscriptionRequest;
import com.careminder.backend.model.chat.ChatMessage;
import com.careminder.backend.model.chat.MessageType;
import lombok.Builder;

@Builder
public record ChatJoinMessageRequest(
        long roomId
) {

    public ChatMessage toEntity(final Long accountMappingId, final String senderName){
        return ChatMessage.builder()
                .accountMappingId(accountMappingId)
                .roomId(roomId)
                .type(MessageType.JOIN)
                .senderName(senderName)
                .content(senderName + "님이 입장했습니다.")
                .build();
    }
}
