package com.careminder.backend.repository.account;

import com.careminder.backend.model.account.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<String, Staff> {
}
