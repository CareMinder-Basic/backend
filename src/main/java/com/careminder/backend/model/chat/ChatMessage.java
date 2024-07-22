package com.careminder.backend.model.chat;

import com.careminder.backend.global.annotation.Association;
import com.careminder.backend.model.account.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class ChatMessage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_message_id")
    private Long id;
    @Association
    @Column(name = "chat_room_id")
    private Long roomId;
    @Association(description = "채팅을 친 계정을 찾기 위한 id")
    private Long accountMappingId;
    private String senderName;
    @Enumerated(EnumType.STRING)
    private MessageType type;
    private String content;

    protected ChatMessage() {
    }

    @Builder
    public ChatMessage(final long roomId, final Long accountMappingId, final String senderName, final MessageType type, final String content) {
        this.roomId = roomId;
        this.accountMappingId = accountMappingId;
        this.senderName = senderName;
        this.type = type;
        this.content = content;
    }
}
