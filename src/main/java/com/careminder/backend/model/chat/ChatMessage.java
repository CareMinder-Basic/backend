package com.careminder.backend.model.chat;

import com.careminder.backend.global.annotation.Association;
import com.careminder.backend.global.base_entity.CreatedTimeEntity;
import com.careminder.backend.model.account.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class ChatMessage extends CreatedTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Association(description = "환자 요청 id, 채팅이 이뤄지고 있는 roomId 역할")
    private Long patientRequestId;
    @Association(description = "채팅을 친 계정을 찾기 위한 id")
    private Long accountId;
    @Association(description = "채팅을 친 계정의 역할")
    private Role role;
    private String senderName;
    @Enumerated(EnumType.STRING)
    private MessageType type;
    private String content;

    protected ChatMessage() {
    }

    @Builder
    public ChatMessage(final long patientRequestId, final Long accountId, final String senderName, final MessageType type,
                       final String content, final Role role) {
        this.patientRequestId = patientRequestId;
        this.accountId = accountId;
        this.senderName = senderName;
        this.type = type;
        this.content = content;
        this.role = role;
    }
}
