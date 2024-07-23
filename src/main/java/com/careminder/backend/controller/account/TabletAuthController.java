package com.careminder.backend.controller.account;

import com.careminder.backend.dto.account.TabletCrateRequest;
import com.careminder.backend.global.annotation.CurrentUser;
import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.global.response.JWTResponse;
import com.careminder.backend.service.account.TabletAuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/tablets")
@RestController
public class TabletAuthController {

    private final TabletAuthService tabletAuthService;

    public TabletAuthController(TabletAuthService tabletAuthService) {
        this.tabletAuthService = tabletAuthService;
    }

    @PostMapping
    public JWTResponse createTablet(
            @CurrentUser final CustomUserDetails customUserDetails,
            @RequestBody final TabletCrateRequest tabletCrateRequest){
        return tabletAuthService.createTablet(customUserDetails,tabletCrateRequest);
    }
}
