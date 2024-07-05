package com.careminder.backend.implement.account;

import com.careminder.backend.dto.account.WardLoginRequest;
import com.careminder.backend.dto.account.WardSignUpRequest;
import com.careminder.backend.global.annotation.Implement;
import com.careminder.backend.global.auth.JWTUtil;
import com.careminder.backend.global.error.exception.InvalidCredentialsException;
import com.careminder.backend.global.response.JWTResponse;
import com.careminder.backend.model.account.Ward;
import com.careminder.backend.repository.account.WardRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static com.careminder.backend.global.constant.AuthExceptionConstant.PASSWORD_ERROR;

@Implement
public class WardAuthManager {

    private final WardRepository wardRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public WardAuthManager(WardRepository wardRepository, BCryptPasswordEncoder passwordEncoder, JWTUtil jwtUtil) {
        this.wardRepository = wardRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public JWTResponse login(final WardLoginRequest wardLoginRequest){
        Ward ward = wardRepository.findByLoginId(wardLoginRequest.loginId());
        if(!passwordEncoder.matches(wardLoginRequest.password(),ward.getPassword())){
            throw new InvalidCredentialsException(PASSWORD_ERROR.message());
        }
        return jwtUtil.createJWT(ward.getId());
    }

    public void logout(){

    }

    @Transactional
    public void signUp(final WardSignUpRequest wardSignUpRequest){
        String encodePassword = passwordEncode(wardSignUpRequest.password());
        wardRepository.save(wardSignUpRequest.toEntity(encodePassword));
    }

    private String passwordEncode(final String password){
        return passwordEncoder.encode(password);
    }

}
