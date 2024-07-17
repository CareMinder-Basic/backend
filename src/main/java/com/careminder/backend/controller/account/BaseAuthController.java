package com.careminder.backend.controller.account;

import com.careminder.backend.dto.account.CurrentUserResponse;
import com.careminder.backend.global.annotation.CurrentUser;
import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.service.account.BaseAuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
public class BaseAuthController {

    private final BaseAuthService baseAuthService;

    public BaseAuthController(final BaseAuthService baseAuthService) {
        this.baseAuthService = baseAuthService;
    }

    @GetMapping("/info")
    public CurrentUserResponse getInfo(@CurrentUser final CustomUserDetails userDetails){
        return baseAuthService.getInfo(userDetails);
    }
}
