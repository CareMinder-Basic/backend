package com.careminder.backend.implement.account;

import com.careminder.backend.dto.account.StaffLoginRequest;
import com.careminder.backend.dto.account.StaffSignUpRequest;
import com.careminder.backend.global.annotation.Implement;
import com.careminder.backend.global.auth.JWTUtil;
import com.careminder.backend.global.error.exception.InvalidCredentialsException;
import com.careminder.backend.global.response.JWTResponse;
import com.careminder.backend.model.account.Staff;
import com.careminder.backend.repository.account.StaffRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static com.careminder.backend.global.constant.AuthExceptionConstant.PASSWORD_ERROR;

@Implement
public class StaffAuthManager {

    private final StaffRepository staffRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public StaffAuthManager(StaffRepository staffRepository, BCryptPasswordEncoder passwordEncoder, JWTUtil jwtUtil) {
        this.staffRepository = staffRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public JWTResponse login(final StaffLoginRequest staffLoginRequest){
        Staff staff = staffRepository.findByLoginId(staffLoginRequest.loginId());
        if(!passwordEncoder.matches(staffLoginRequest.password(),staff.getPassword())){
            throw new InvalidCredentialsException(PASSWORD_ERROR.message());
        }
        return jwtUtil.createJWT(staff.getId());
    }

    public void logout(){

    }

    @Transactional
    public void signUp(final StaffSignUpRequest staffSignUpRequest){
        String encodePassword = passwordEncode(staffSignUpRequest.password());
        staffRepository.save(staffSignUpRequest.toEntity(encodePassword));
    }

    private String passwordEncode(final String password){
        return passwordEncoder.encode(password);
    }
}
