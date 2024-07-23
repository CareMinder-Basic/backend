package com.careminder.backend.service.account;

import com.careminder.backend.dto.account.PatientCrateRequest;
import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.implement.account.patient.PatientManager;
import com.careminder.backend.implement.account.tablet.TabletManager;
import com.careminder.backend.model.account.Patient;
import com.careminder.backend.model.account.Tablet;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private final PatientManager patientManager;
    private final TabletManager tabletManager;

    public PatientService(PatientManager patientManager, TabletManager tabletManager) {
        this.patientManager = patientManager;
        this.tabletManager = tabletManager;
    }

    public void createPatient(final CustomUserDetails customUserDetails, final PatientCrateRequest patientCrateRequest) {
        Patient patient = patientManager.createPatient(patientCrateRequest);
        tabletManager.updatePatientId(customUserDetails.getAccountId(),patient.getId());
    }
}
