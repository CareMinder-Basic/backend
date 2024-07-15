package com.careminder.backend.model.chat;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import study.chat.global.annotation.Association;

@Getter
@Entity
public class ChatRoom {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private long id;
    @Association
    private long memberId;
    private String name;

    protected ChatRoom(){}

    @Builder
    public ChatRoom(final long memberId, final String name) {
        this.memberId = memberId;
        this.name = name;
    }
}
