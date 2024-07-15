package com.careminder.backend.model.chat;

import com.careminder.backend.global.annotation.Association;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class ChatMessage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_message_id")
    private long id;
    @Association
    @Column(name = "chat_room_id")
    private long roomId;
    @Association @Column(name = "member_id")
    private long memberId;
    private MessageType type;
    private String content;
    private String sender;

    protected ChatMessage() {
    }

    @Builder
    public ChatMessage(final long roomId, final long memberId, final MessageType type, final String content, final String sender) {
        this.roomId = roomId;
        this.memberId = memberId;
        this.type = type;
        this.content = content;
        this.sender = sender;
    }
}
