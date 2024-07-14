package com.careminder.backend.model.account;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mail_id", unique = true, nullable = false)
    private Long id;

    // 이메일 주소
    @Column(name = "mail", nullable = false)
    private String mail;

    // 이메일 인증 여부
    @Column(name = "mail_status", nullable = false)
    private boolean mailStatus;

    protected Mail() {
    }

    @Builder
    public Mail(String mail) {
        this.mail = mail;
        this.mailStatus = false;
    }
}
