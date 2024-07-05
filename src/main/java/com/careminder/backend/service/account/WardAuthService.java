package com.careminder.backend.service.account;

import com.careminder.backend.dto.account.WardLoginRequest;
import com.careminder.backend.dto.account.WardSignUpRequest;
import com.careminder.backend.global.response.JWTResponse;
import com.careminder.backend.implement.account.WardAuthManager;
import org.springframework.stereotype.Service;

@Service
public class WardAuthService {

    private final WardAuthManager wardAuthManager;

    public WardAuthService(WardAuthManager wardAuthManager) {
        this.wardAuthManager = wardAuthManager;
    }

    public JWTResponse login(final WardLoginRequest wardLoginRequest){
        return wardAuthManager.login(wardLoginRequest);
    }

    public void logout(){

    }

    public void signUp(final WardSignUpRequest wardSignUpRequest){
        // db 저장
        wardAuthManager.signUp(wardSignUpRequest);
    }

}
