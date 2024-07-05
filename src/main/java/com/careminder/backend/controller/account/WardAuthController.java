package com.careminder.backend.controller.account;

import com.careminder.backend.dto.account.WardLoginRequest;
import com.careminder.backend.dto.account.WardSignUpRequest;
import com.careminder.backend.global.response.JWTResponse;
import com.careminder.backend.service.account.WardAuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/ward")
@RestController
public class WardAuthController {

    private final WardAuthService wardAuthService;

    public WardAuthController(WardAuthService wardAuthService) {
        this.wardAuthService = wardAuthService;
    }

    @PostMapping("/login")
    public JWTResponse login(@RequestBody final WardLoginRequest wardLoginRequest){
        return wardAuthService.login(wardLoginRequest);
    }

    @PostMapping("/logout")
    public void logout(){

    }

    @PostMapping("/sign-up")
    public void signUp(@Valid @RequestBody final WardSignUpRequest wardSignUpRequest){
        wardAuthService.signUp(wardSignUpRequest);
    }
}
