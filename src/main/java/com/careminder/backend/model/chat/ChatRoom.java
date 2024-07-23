package com.careminder.backend.model.chat;

import com.careminder.backend.global.annotation.Association;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class ChatRoom {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomName;
    @Association(description = "해당 채팅의 요청 id")
    private Long patientRequestId;

    protected ChatRoom(){}

    @Builder
    public ChatRoom(final String roomName, final Long patientRequestId) {
        this.patientRequestId = patientRequestId;
        this.roomName = roomName;
    }
}
