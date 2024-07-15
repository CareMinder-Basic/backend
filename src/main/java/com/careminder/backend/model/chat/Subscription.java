package com.careminder.backend.model.chat;

import jakarta.persistence.*;
import lombok.Builder;
import study.chat.global.annotation.Association;

@Entity
public class Subscription {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private long id;
    @Association @Column(name = "chat_room_id")
    private long roomId;
    @Association
    private long memberId;

    protected Subscription(){}

    @Builder
    public Subscription(final long roomId, final long memberId) {
        this.roomId = roomId;
        this.memberId = memberId;
    }
}
