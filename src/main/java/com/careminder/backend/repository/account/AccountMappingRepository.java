package com.careminder.backend.repository.account;

import com.careminder.backend.model.account.AccountMapping;
import com.careminder.backend.model.account.Role;
import org.springframework.stereotype.Repository;

@Repository
public class AccountMappingRepository {

    private final AccountMappingJpaRepository accountMappingJpaRepository;

    public AccountMappingRepository(final AccountMappingJpaRepository accountMappingJpaRepository) {
        this.accountMappingJpaRepository = accountMappingJpaRepository;
    }

    public Long findOrCreateAccountMappingId(final Role role, final Long accountId) {
        return accountMappingJpaRepository.findByRoleAndAccountId(role, accountId)
                .map(AccountMapping::getId)
                .orElseGet(() -> {
                    AccountMapping accountMapping = AccountMapping.builder()
                            .role(role)
                            .accountId(accountId)
                            .build();
                    accountMappingJpaRepository.save(accountMapping);
                    return accountMapping.getId();
                });
    }

    public Long getAccountId(final Long accountMappingId){
        return accountMappingJpaRepository.findAccountIdById(accountMappingId);
    }

    public void save(final AccountMapping accountMapping){
        accountMappingJpaRepository.save(accountMapping);
    }
}
