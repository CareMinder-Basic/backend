package com.careminder.backend.service.request;

import com.careminder.backend.dto.request.PatientRequestAppendRequest;
import com.careminder.backend.implement.request.PatientRequestManager;
import org.springframework.stereotype.Service;

@Service
public class PatientRequestService {

    private final PatientRequestManager patientRequestManager;

    public PatientRequestService(PatientRequestManager patientRequestManager) {
        this.patientRequestManager = patientRequestManager;
    }

    public void append(final Long tabletId, final PatientRequestAppendRequest patientRequestAppendRequest) {
        patientRequestManager.append(patientRequestAppendRequest.toEntity(tabletId));
    }
}
