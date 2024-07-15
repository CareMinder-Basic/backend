package com.careminder.backend.repository.chat.chat_room;

import com.careminder.backend.model.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomJpaRepository extends JpaRepository<ChatRoom, Long> {

    void deleteByMemberIdAndId(final long memberId, final long roomId);
}
