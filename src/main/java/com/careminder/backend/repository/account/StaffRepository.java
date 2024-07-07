package com.careminder.backend.repository.account;

import com.careminder.backend.model.account.Staff;
import com.careminder.backend.model.account.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findByLoginId(String LoginId);
}
