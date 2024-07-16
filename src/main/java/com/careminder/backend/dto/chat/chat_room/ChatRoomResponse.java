package com.careminder.backend.dto.chat.chat_room;

import com.careminder.backend.model.chat.ChatRoom;
import lombok.Builder;

@Builder
public record ChatRoomResponse(
        long id,
        long ownerId,
        String name
) {

    public static ChatRoomResponse from(final ChatRoom chatRoom){
        return ChatRoomResponse.builder()
                .id(chatRoom.getId())
                .ownerId(chatRoom.getAccountMappingId())
                .name(chatRoom.getRoomName())
                .build();
    }
}
