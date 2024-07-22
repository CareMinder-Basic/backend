package com.careminder.backend.repository.account;

import com.careminder.backend.model.account.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WardJpaRepository extends JpaRepository<Ward, Long> {
    Optional<Ward> findByLoginId(String LoginId);
}
