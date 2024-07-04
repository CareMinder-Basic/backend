package com.careminder.backend.repository.account;

import com.careminder.backend.model.account.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WardRepository extends JpaRepository<Ward, Long> {
}
