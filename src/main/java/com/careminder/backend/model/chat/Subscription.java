package com.careminder.backend.model.chat;

import com.careminder.backend.global.annotation.Association;
import com.careminder.backend.model.account.Role;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
public class Subscription {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private Long id;
    @Association
    @Column(name = "chat_room_id")
    private Long roomId;
    @Association(description = "채팅방을 구독한 계정을 찾기 위한 id")
    private Long accountMappingId;

    protected Subscription(){}

    @Builder
    public Subscription(final long roomId, final long accountMappingId) {
        this.roomId = roomId;
        this.accountMappingId = accountMappingId;
    }
}
