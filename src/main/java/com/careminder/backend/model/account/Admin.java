package com.careminder.backend.model.account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 관리자의 이름 */
    private String name;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private String nfc;
    private String fingerprint;
    private Long hospitalId;

}
