package com.careminder.backend.model.chat;

import com.careminder.backend.global.annotation.Association;
import com.careminder.backend.model.account.Role;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
public class Subscription {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Association(description = "환자 요청 id, 채팅이 이뤄지고 있는 roomId 역할")
    private Long patientRequestId;
    @Association(description = "채팅방을 구독한 계정을 찾기 위한 id")
    private Long accountId;
    @Association(description = "채팅방을 구독한 계정의 역할")
    private Role role;

    protected Subscription(){}

    @Builder
    public Subscription(final Long patientRequestId, final Long accountId, final Role role) {
        this.patientRequestId = patientRequestId;
        this.accountId = accountId;
        this.role = role;
    }
}
