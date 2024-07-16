package com.careminder.backend.model.account;

import jakarta.persistence.*;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 관리자의 이름 */
    private String name;
    private String loginId;
    private String password;
    private String phoneNumber;
    private String email;
    private String nfc;
    private String fingerprint;
    private Long hospitalId;

}
