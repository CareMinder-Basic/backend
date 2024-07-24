package com.careminder.backend.model.account;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Ward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ward_id")
    private Long id;

    /** 병동의 이름 */
    private String wardName;
    private String loginId;
    private String password;
    private String managerName;
    private String managerPhoneNumber;
    private String managerEmail;

    protected Ward() {
    }

    @Builder
    private Ward(String wardName, String loginId, String password, String managerName, String managerPhoneNumber, String managerEmail) {
        this.wardName = wardName;
        this.loginId = loginId;
        this.password = password;
        this.managerName = managerName;
        this.managerPhoneNumber = managerPhoneNumber;
        this.managerEmail = managerEmail;
    }
}
