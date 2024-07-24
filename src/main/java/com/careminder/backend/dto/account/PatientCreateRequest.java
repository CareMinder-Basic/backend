package com.careminder.backend.dto.account;

import com.careminder.backend.model.account.constant.Gender;
import com.careminder.backend.model.account.Patient;

public record PatientCreateRequest(
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