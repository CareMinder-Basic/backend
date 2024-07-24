package com.careminder.backend.service.chat.request;

import com.careminder.backend.dto.chat.request.PatientRequestAppendRequest;
import com.careminder.backend.implement.chat.request.PatientRequestManager;
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