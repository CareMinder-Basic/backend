package com.careminder.backend.model.account;

import jakarta.persistence.*;

@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 직원의 이름 */
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String loginId;
    private String password;
    private String phoneNumber;
    private String email;
    private String nfc;
    private String fingerprint;
    @Enumerated(EnumType.STRING)
    private StaffRole staffRole;
}
