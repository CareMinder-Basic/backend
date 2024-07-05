package com.careminder.backend.repository.account;

import com.careminder.backend.model.account.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
