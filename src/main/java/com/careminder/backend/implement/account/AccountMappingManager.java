package com.careminder.backend.implement.account;

import com.careminder.backend.global.annotation.Implement;
import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.model.account.AccountMapping;
import com.careminder.backend.model.account.Role;
import com.careminder.backend.repository.account.AccountMappingRepository;
import org.springframework.transaction.annotation.Transactional;

@Implement
public class AccountMappingManager {

    private final AccountMappingRepository accountMappingRepository;

    public AccountMappingManager(final AccountMappingRepository accountMappingRepository) {
        this.accountMappingRepository = accountMappingRepository;
    }

    @Transactional
    public Long findOrCreateAccountMappingId(final CustomUserDetails customUserDetails) {
        Role role = customUserDetails.getRole();
        Long accountId = customUserDetails.getAccountId();
        return accountMappingRepository.findOrCreateAccountMappingId(role, accountId);
    }

    @Transactional(readOnly = true)
    public Long getAccountId(final Long accountMappingId){
        return accountMappingRepository.getAccountId(accountMappingId);
    }

    @Transactional
    public void save(final Role role, final Long accountId){
        AccountMapping accountMapping = AccountMapping.builder()
                .role(role).accountId(accountId)
                .build();
        accountMappingRepository.save(accountMapping);
    }
}
