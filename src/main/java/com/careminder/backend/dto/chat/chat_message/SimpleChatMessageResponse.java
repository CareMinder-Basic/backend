package com.careminder.backend.dto.chat.chat_message;

import lombok.Builder;
import study.chat.entity.ChatMessage;

@Builder
public record SimpleChatMessageResponse(
        long memberId,
        String content
) {

    public static SimpleChatMessageResponse from(final SimpleChatMessage simpleChatMessage){
        return new SimpleChatMessageResponse(simpleChatMessage.memberId(), simpleChatMessage.content());
    }

    public static SimpleChatMessageResponse from(final ChatMessage chatMessage){
        return SimpleChatMessageResponse.builder()
                .content(chatMessage.getContent())
                .memberId(chatMessage.getMemberId())
                .build();
    }
}
