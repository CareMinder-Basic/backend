package com.careminder.backend.service.account;

import com.careminder.backend.dto.account.StaffLoginRequest;
import com.careminder.backend.dto.account.StaffSignUpRequest;
import com.careminder.backend.dto.account.WardLoginRequest;
import com.careminder.backend.dto.account.WardSignUpRequest;
import com.careminder.backend.global.response.JWTResponse;
import com.careminder.backend.implement.account.StaffAuthManager;
import com.careminder.backend.implement.account.WardAuthManager;
import org.springframework.stereotype.Service;

@Service
public class StaffAuthService {

    private final StaffAuthManager staffAuthManager;

    public StaffAuthService(StaffAuthManager staffAuthManager) {
        this.staffAuthManager = staffAuthManager;
    }

    public JWTResponse login(final StaffLoginRequest staffLoginRequest){
        return staffAuthManager.login(staffLoginRequest);
    }

    public void logout(){

    }

    public void signUp(final StaffSignUpRequest staffSignUpRequest){
        // db 저장
        staffAuthManager.signUp(staffSignUpRequest);
    }
}
