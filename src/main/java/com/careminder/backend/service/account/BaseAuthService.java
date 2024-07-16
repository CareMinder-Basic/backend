package com.careminder.backend.service.account;

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

    public String getName(Long userId, Role role){
        BaseAuthManager authManager = authManagerFactory.getAuthManager(role);
        return authManager.getName(userId);
    }
}
