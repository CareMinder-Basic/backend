package com.careminder.backend.controller.account;

import com.careminder.backend.dto.account.StaffLoginRequest;
import com.careminder.backend.dto.account.StaffSignUpRequest;
import com.careminder.backend.dto.account.WardLoginRequest;
import com.careminder.backend.dto.account.WardSignUpRequest;
import com.careminder.backend.global.response.JWTResponse;
import com.careminder.backend.service.account.StaffAuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/staff")
@RestController
public class StaffAuthController {
    private final StaffAuthService staffAuthService;

    public StaffAuthController(StaffAuthService staffAuthService) {
        this.staffAuthService = staffAuthService;
    }

    @PostMapping("/login")
    public JWTResponse login(@RequestBody final StaffLoginRequest staffLoginRequest){
        return staffAuthService.login(staffLoginRequest);
    }

    @PostMapping("/logout")
    public void logout(){

    }

    @PostMapping("/sign-up")
    public void signUp(@Valid @RequestBody final StaffSignUpRequest staffSignUpRequest){
        staffAuthService.signUp(staffSignUpRequest);
    }
}
