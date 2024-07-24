package com.careminder.backend.service.chat.request;

import com.careminder.backend.dto.chat.request.PatientRequestAppendRequest;
import com.careminder.backend.dto.chat.request.PatientRequestResponse;
import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.implement.chat.request.PatientRequestManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientRequestService {

    private final PatientRequestManager patientRequestManager;

    public PatientRequestService(PatientRequestManager patientRequestManager) {
        this.patientRequestManager = patientRequestManager;
    }

    public void append(final Long tabletId, final PatientRequestAppendRequest patientRequestAppendRequest) {
        patientRequestManager.append(patientRequestAppendRequest.toEntity(tabletId));
    }

    public List<PatientRequestResponse> getAllPatientRequest(){
        return patientRequestManager.getPatientRequests();
    }
}
