package com.careminder.backend.dto.chat.request;

import com.careminder.backend.model.chat.request.PatientRequest;
import com.careminder.backend.model.chat.request.RequestStatus;

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
