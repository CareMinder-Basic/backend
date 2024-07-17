package com.careminder.backend.service.account;

import com.careminder.backend.dto.account.CurrentUserResponse;
import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.implement.account.AuthManagerFactory;
import com.careminder.backend.implement.account.BaseAuthManager;
import com.careminder.backend.model.account.Role;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BaseAuthService {

    private final AuthManagerFactory authManagerFactory;

    public BaseAuthService(final AuthManagerFactory authManagerFactory) {
        this.authManagerFactory = authManagerFactory;
    }

    public String getName(final Long accountId, final Role role){
        BaseAuthManager authManager = authManagerFactory.getAuthManager(role);
        return authManager.getName(accountId);
    }

    public CurrentUserResponse getInfo(final CustomUserDetails userDetails) {
        Long accountId = userDetails.getAccountId();
        Role role = userDetails.getRole();
        String name = getName(accountId, role);
        return CurrentUserResponse.builder()
                .accountId(accountId)
                .role(role)
                .name(name)
                .build();
    }
}
