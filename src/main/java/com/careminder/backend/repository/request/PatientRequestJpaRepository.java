package com.careminder.backend.repository.request;

import com.careminder.backend.model.request.PatientRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRequestJpaRepository extends JpaRepository<PatientRequest, Long> {
}
