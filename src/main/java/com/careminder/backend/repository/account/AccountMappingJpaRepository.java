package com.careminder.backend.repository.account;

import com.careminder.backend.model.account.AccountMapping;
import com.careminder.backend.model.account.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountMappingJpaRepository extends JpaRepository<AccountMapping, Long> {
    Optional<AccountMapping> findByRoleAndAccountId(final Role role, final Long accountId);
    Long findAccountIdById(final Long accountMappingId);
}
