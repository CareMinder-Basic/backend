package com.careminder.backend.repository.chat.request;

import com.careminder.backend.model.chat.request.PatientRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRequestJpaRepository extends JpaRepository<PatientRequest, Long> {
}
