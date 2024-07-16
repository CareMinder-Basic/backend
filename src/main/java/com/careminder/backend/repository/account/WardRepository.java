package com.careminder.backend.repository.account;

import com.careminder.backend.global.constant.exception.WardExceptionConstant;
import com.careminder.backend.global.error.exception.NotFoundException;
import com.careminder.backend.model.account.Staff;
import com.careminder.backend.model.account.Ward;
import org.springframework.stereotype.Repository;

import static com.careminder.backend.global.constant.exception.WardExceptionConstant.WARD_NOT_FOUND_ERROR;

@Repository
public class WardRepository {

    private final WardJpaRepository wardJpaRepository;

    public WardRepository(WardJpaRepository wardJpaRepository) {
        this.wardJpaRepository = wardJpaRepository;
    }

    public void save(final Ward ward){
        wardJpaRepository.save(ward);
    }

    public Ward getByLoginId(final String loginId){
        return wardJpaRepository.findByLoginId(loginId).orElseThrow(() ->
                new NotFoundException(WARD_NOT_FOUND_ERROR.message()));
    }

    public Ward getById(final Long id){
        return wardJpaRepository.findById(id).orElseThrow(() ->
                new NotFoundException(WARD_NOT_FOUND_ERROR.message()));
    }
}
