package com.careminder.backend.model.account;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
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
    @Enumerated(EnumType.STRING)
    private StaffRole staffRole;

    protected Staff() {
    }

    @Builder
    public Staff(String name, String loginId, String password, String phoneNumber, String email, String nfc, String fingerprint, StaffRole staffRole) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.nfc = nfc;
        this.fingerprint = fingerprint;
        this.staffRole = staffRole;
    }
}
