package com.careminder.backend.dto.chat.chat_room;

public record ChatRoomLeaveRequest(
        long memberId,
        long roomId
) {
}
