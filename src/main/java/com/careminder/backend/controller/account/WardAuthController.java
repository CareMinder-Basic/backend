package com.careminder.backend.controller.account;

import com.careminder.backend.dto.account.WardSignUpRequest;
import com.careminder.backend.service.account.WardAuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class WardAuthController {

    private final WardAuthService wardAuthService;

    public WardAuthController(WardAuthService wardAuthService) {
        this.wardAuthService = wardAuthService;
    }

    @PostMapping
    public void login(){

    }

    @PostMapping
    public void logout(){

    }

    @PostMapping
    public void signUp(@Valid @RequestBody final WardSignUpRequest wardSignUpRequest){
        wardAuthService.signUp(wardSignUpRequest);
    }
}
