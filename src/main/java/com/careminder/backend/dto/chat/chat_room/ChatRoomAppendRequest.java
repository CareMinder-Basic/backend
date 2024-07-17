package com.careminder.backend.dto.chat.chat_room;

import com.careminder.backend.model.chat.ChatRoom;

public record ChatRoomAppendRequest(
        String roomName
) {

    public ChatRoom toEntity(final Long accountMappingId){
        return ChatRoom.builder()
                .roomName(roomName)
                .accountMappingId(accountMappingId)
                .build();
    }
}
