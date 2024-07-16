package com.careminder.backend.repository.account;

import com.careminder.backend.global.error.exception.NotFoundException;
import com.careminder.backend.model.account.Staff;
import org.springframework.stereotype.Repository;

import static com.careminder.backend.global.constant.exception.StaffExceptionConstant.STAFF_NOT_FOUND_ERROR;

@Repository
public class StaffRepository {

    private final StaffJpaRepository staffJpaRepository;

    public StaffRepository(final StaffJpaRepository staffJpaRepository) {
        this.staffJpaRepository = staffJpaRepository;
    }

    public void save(final Staff staff){
        staffJpaRepository.save(staff);
    }

    public Staff getByLoginId(final String loginId){
        return staffJpaRepository.findByLoginId(loginId).orElseThrow(() ->
                new NotFoundException(STAFF_NOT_FOUND_ERROR.message()));
    }

    public Staff getById(final Long id){
        return staffJpaRepository.findById(id).orElseThrow(() ->
                new NotFoundException(STAFF_NOT_FOUND_ERROR.message()));
    }
}
