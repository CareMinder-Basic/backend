package com.careminder.backend.controller.account;

import com.careminder.backend.dto.account.WardLoginRequest;
import com.careminder.backend.global.response.JWTResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/tablets")
@RestController
public class TabletController {

    private final 

    @PostMapping("/login")
    public JWTResponse login(@RequestBody final WardLoginRequest wardLoginRequest){
        return wardAuthService.login(wardLoginRequest);
    }

}
