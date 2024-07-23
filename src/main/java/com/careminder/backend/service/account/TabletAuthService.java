package com.careminder.backend.service.account;

import com.careminder.backend.dto.account.TabletCrateRequest;
import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.global.response.JWTResponse;
import com.careminder.backend.implement.account.tablet.TabletAuthManager;
import org.springframework.stereotype.Service;

@Service
public class TabletAuthService {

    private final TabletAuthManager tabletAuthManager;

    public TabletAuthService(TabletAuthManager tabletAuthManager) {
        this.tabletAuthManager = tabletAuthManager;
    }

    public JWTResponse createTablet(final CustomUserDetails customUserDetails, final TabletCrateRequest tabletCrateRequest) {
        return tabletAuthManager.createTablet(customUserDetails,tabletCrateRequest);
    }
}
