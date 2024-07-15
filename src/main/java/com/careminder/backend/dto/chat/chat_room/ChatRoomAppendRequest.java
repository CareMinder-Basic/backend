package com.careminder.backend.dto.chat.chat_room;

import com.careminder.backend.model.chat.ChatRoom;

public record ChatRoomAppendRequest(
        long memberId,
        String name
) {

    public ChatRoom toEntity(){
        return ChatRoom.builder()
                .name(this.name)
                .memberId(this.memberId)
                .build();
    }
}
