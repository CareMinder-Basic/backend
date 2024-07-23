package com.careminder.backend.dto.request;

import com.careminder.backend.model.account.Patient;
import com.careminder.backend.model.request.PatientRequest;
import com.careminder.backend.model.request.RequestStatus;

public record PatientRequestAppendRequest(
    Long staffId,
    String content
) {

    public PatientRequest toEntity(final Long tabletId){
        return PatientRequest.builder()
                .content(content)
                .staffId(staffId)
                .status(RequestStatus.PENDING)
                .tabletId(tabletId)
                .build();
    }
}
