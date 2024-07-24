package com.careminder.backend.implement.chat.request;

import com.careminder.backend.dto.chat.request.PatientRequestResponse;
import com.careminder.backend.global.annotation.Implement;
import com.careminder.backend.model.chat.request.PatientRequest;
import com.careminder.backend.repository.chat.request.PatientRequestRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Implement
public class PatientRequestManager {

    private final PatientRequestRepository patientRequestRepository;

    public PatientRequestManager(PatientRequestRepository patientRequestRepository) {
        this.patientRequestRepository = patientRequestRepository;
    }

    @Transactional
    public void append(final PatientRequest entity) {
        patientRequestRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public List<PatientRequestResponse> getPatientRequests(){
        return patientRequestRepository.getPatientRequests();
    }
}
