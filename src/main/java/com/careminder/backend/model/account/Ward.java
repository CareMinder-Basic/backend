package com.careminder.backend.model.account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ward {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /** 병동의 이름 */
    private String name;
    private String role;
    private String loginId;
    private String password;
    private String phoneNumber;
}
