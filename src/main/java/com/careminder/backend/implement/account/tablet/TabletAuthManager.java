package com.careminder.backend.implement.account.tablet;

import com.careminder.backend.dto.account.TabletCrateRequest;
import com.careminder.backend.global.annotation.Implement;
import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.global.auth.JWTUtil;
import com.careminder.backend.global.response.JWTResponse;
import com.careminder.backend.implement.account.BaseAuthManager;
import com.careminder.backend.model.account.constant.Role;
import com.careminder.backend.model.account.Tablet;
import com.careminder.backend.repository.account.TabletRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Implement
public class TabletAuthManager implements BaseAuthManager {

    private final TabletRepository tabletRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public TabletAuthManager(final TabletRepository tabletRepository,
                           final BCryptPasswordEncoder passwordEncoder,
                           final JWTUtil jwtUtil) {
        this.tabletRepository = tabletRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String getName(final Long id) {
        Tablet tablet = tabletRepository.getById(id);
        return tablet.getTabletName();
    }

    public void logout(){

    }

    @Transactional
    public JWTResponse createTablet(final CustomUserDetails customUserDetails, final TabletCrateRequest tabletCrateRequest){
        Tablet tablet = tabletRepository.save(tabletCrateRequest.toEntity(customUserDetails.getAccountId()));
        return jwtUtil.createJWT(tablet.getId(), Role.Tablet);
    }

    private String passwordEncode(final String password){
        return passwordEncoder.encode(password);
    }
}
