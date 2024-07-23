package com.careminder.backend.dto.account;

import com.careminder.backend.model.account.Gender;
import com.careminder.backend.model.account.Patient;

public record PatientCrateRequest(
        String name,
        String phoneNumber,
        Gender gender
) {
    public Patient toEntity() {
        Patient.PatientBuilder patientBuilder = Patient.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .gender(gender);

        return patientBuilder.build();
    }
}