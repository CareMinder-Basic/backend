package com.careminder.backend.implement.account.patient;

import com.careminder.backend.dto.account.PatientCrateRequest;
import com.careminder.backend.global.annotation.Implement;
import com.careminder.backend.model.account.Patient;
import com.careminder.backend.repository.account.PatientRepository;
import org.springframework.transaction.annotation.Transactional;

@Implement
public class PatientManager{

    private final PatientRepository patientRepository;

    public PatientManager(final PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional
    public Patient createPatient(final PatientCrateRequest patientCrateRequest){
        return patientRepository.save(patientCrateRequest.toEntity());
    }
}
