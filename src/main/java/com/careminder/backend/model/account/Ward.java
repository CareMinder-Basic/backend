package com.careminder.backend.model.account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ward {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 병동의 이름 */
    private String wardName;
    private Role role;
    private String loginId;
    private String password;
    private String managerName;
    private String managerPhoneNumber;
    private String managerEmail;

}
