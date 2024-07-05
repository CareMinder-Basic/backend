package com.careminder.backend.model.account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 직원의 이름 */
    private String name;
    private String loginId;
    private String password;
    private String phoneNumber;
    private String email;
    private String nfc;
    private String fingerprint;
    private StaffRole role;
}
