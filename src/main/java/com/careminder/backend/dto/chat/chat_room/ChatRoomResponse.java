package com.careminder.backend.dto.chat.chat_room;

import com.careminder.backend.model.chat.ChatRoom;
import lombok.Builder;

@Builder
public record ChatRoomResponse(
        long id,
        long accountMappingId,
        String roomName
) {

    public static ChatRoomResponse from(final ChatRoom chatRoom){
        return ChatRoomResponse.builder()
                .id(chatRoom.getId())
                .accountMappingId(chatRoom.getAccountMappingId())
                .roomName(chatRoom.getRoomName())
                .build();
    }
}
