package com.careminder.backend.repository.account;

import com.careminder.backend.model.account.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffJpaRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByLoginId(String loginId);
}
