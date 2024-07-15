package com.careminder.backend.dto.chat.chat_message;

import com.careminder.backend.dto.chat.subscription.UnsubscribeRequest;
import com.careminder.backend.model.chat.ChatMessage;
import com.careminder.backend.model.chat.MessageType;
import lombok.Builder;

@Builder
public record ChatLeaveMessageRequest(
        long memberId,
        long roomId
) {

    public ChatMessage toEntity(final String sender) {
        return ChatMessage.builder()
                .memberId(memberId)
                .roomId(roomId)
                .type(MessageType.LEAVE)
                .sender(sender)
                .content(sender + "님이 퇴장했습니다.")
                .build();
    }

    public static ChatLeaveMessageRequest from(final UnsubscribeRequest unsubscribeRequest){
        return ChatLeaveMessageRequest.builder()
                .memberId(unsubscribeRequest.memberId())
                .roomId(unsubscribeRequest.roomId())
                .build();
    }
}