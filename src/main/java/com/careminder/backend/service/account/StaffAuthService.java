package com.careminder.backend.service.account;

import com.careminder.backend.dto.account.*;
import com.careminder.backend.global.response.CollectionApiResponse;
import com.careminder.backend.global.response.JWTResponse;
import com.careminder.backend.implement.account.StaffAuthManager;
import com.careminder.backend.implement.account.WardAuthManager;
import org.springframework.stereotype.Service;

import java.util.List;

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
//        todo: loginId 겹치면 회원가입 불가능하도록 핸들링 
        staffAuthManager.signUp(staffSignUpRequest);
    }

    public List<StaffInfoResponse> getAllStaff() {
        return staffAuthManager.getAllStaff();
    }
}
