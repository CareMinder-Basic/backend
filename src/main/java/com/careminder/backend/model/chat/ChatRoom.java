package com.careminder.backend.model.chat;

import com.careminder.backend.global.annotation.Association;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class ChatRoom {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private Long id;
    @Association(description = "채팅방 주인 계정의 id를 찾기 위한 매핑 id")
    private Long accountMappingId;
    private String roomName;

    protected ChatRoom(){}

    @Builder
    public ChatRoom(final Long accountMappingId, final String roomName) {
        this.accountMappingId = accountMappingId;
        this.roomName = roomName;
    }
}
