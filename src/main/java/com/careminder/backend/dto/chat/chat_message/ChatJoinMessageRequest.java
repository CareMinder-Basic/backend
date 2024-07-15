package com.careminder.backend.dto.chat.chat_message;

import com.careminder.backend.dto.chat.subscription.SubscriptionRequest;
import com.careminder.backend.model.chat.ChatMessage;
import com.careminder.backend.model.chat.MessageType;
import lombok.Builder;

@Builder
public record ChatJoinMessageRequest(
        long memberId,
        long roomId
) {

    public ChatMessage toEntity(final String sender){
        return ChatMessage.builder()
                .memberId(memberId)
                .roomId(roomId)
                .type(MessageType.JOIN)
                .sender(sender)
                .content(sender + "님이 입장했습니다.")
                .build();
    }

    public static ChatJoinMessageRequest from(final SubscriptionRequest subscriptionRequest){
        return ChatJoinMessageRequest.builder()
                .memberId(subscriptionRequest.memberId())
                .roomId(subscriptionRequest.roomId())
                .build();
    }
}
