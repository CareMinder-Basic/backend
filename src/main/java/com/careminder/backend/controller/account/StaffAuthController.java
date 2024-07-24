package com.careminder.backend.controller.account;

import com.careminder.backend.dto.account.*;
import com.careminder.backend.global.annotation.CurrentUser;
import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.global.response.CollectionApiResponse;
import com.careminder.backend.global.response.JWTResponse;
import com.careminder.backend.model.account.Staff;
import com.careminder.backend.service.account.StaffAuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/staff")
@RestController
public class StaffAuthController {

    private final StaffAuthService staffAuthService;

    public StaffAuthController(StaffAuthService staffAuthService) {
        this.staffAuthService = staffAuthService;
    }

    @GetMapping("/info")
    public String staffInfo(@CurrentUser final CustomUserDetails userDetails){
        return String.format("accountId: %d , role: %s" ,userDetails.getAccountId(), userDetails.getRole());
    }

    @GetMapping("/list")
    public CollectionApiResponse<StaffInfoResponse> staffList(){
        return CollectionApiResponse.from(staffAuthService.getAllStaff());
    }

    @PostMapping("/login")
    public JWTResponse login(@RequestBody final StaffLoginRequest staffLoginRequest){
        return staffAuthService.login(staffLoginRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/logout")
    public void logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authorization) {

    }

    @PostMapping("/sign-up")
    public void signUp(@Valid @RequestBody final StaffSignUpRequest staffSignUpRequest){
        staffAuthService.signUp(staffSignUpRequest);
    }
}
