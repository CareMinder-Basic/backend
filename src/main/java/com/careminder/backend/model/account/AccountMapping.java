package com.careminder.backend.model.account;

import com.careminder.backend.global.annotation.Association;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class AccountMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Association(description = "Account 유형의 Entity 의 PK")
    private Long accountId;

    protected  AccountMapping(){

    }

    @Builder
    public AccountMapping(final Role role, final Long accountId) {
        this.role = role;
        this.accountId = accountId;
    }
}
