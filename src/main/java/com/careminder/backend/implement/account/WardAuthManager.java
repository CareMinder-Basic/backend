package com.careminder.backend.implement.account;

import com.careminder.backend.dto.account.WardLoginRequest;
import com.careminder.backend.dto.account.WardSignUpRequest;
import com.careminder.backend.global.annotation.Implement;
import com.careminder.backend.global.auth.JWTUtil;
import com.careminder.backend.global.error.exception.InvalidCredentialsException;
import com.careminder.backend.global.response.JWTResponse;
import com.careminder.backend.model.account.constant.Role;
import com.careminder.backend.model.account.Ward;
import com.careminder.backend.repository.account.WardRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static com.careminder.backend.global.constant.exception.AuthExceptionConstant.PASSWORD_ERROR;

@Implement
public class WardAuthManager implements BaseAuthManager{

    private final WardRepository wardRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public WardAuthManager(final WardRepository wardRepository,
                           final BCryptPasswordEncoder passwordEncoder,
                           final JWTUtil jwtUtil) {
        this.wardRepository = wardRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String getName(final Long id) {
        Ward ward = wardRepository.getById(id);
        return ward.getWardName();
    }

    public JWTResponse login(final WardLoginRequest wardLoginRequest){
        Ward ward = wardRepository.getByLoginId(wardLoginRequest.loginId());
        if(!passwordEncoder.matches(wardLoginRequest.password(),ward.getPassword())){
            throw new InvalidCredentialsException(PASSWORD_ERROR.message());
        }
        return jwtUtil.createJWT(ward.getId(), Role.WARD);
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
