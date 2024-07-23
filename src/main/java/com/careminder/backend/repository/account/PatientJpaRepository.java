package com.careminder.backend.repository.account;

import com.careminder.backend.model.account.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientJpaRepository extends JpaRepository<Patient,Long> {
}
