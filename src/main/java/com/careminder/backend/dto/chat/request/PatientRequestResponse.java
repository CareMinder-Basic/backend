package com.careminder.backend.dto.chat.request;

import com.careminder.backend.model.chat.request.RequestStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

public record PatientRequestResponse(
    Long id,
    RequestStatus requestStatus,
    String content,
    Long staffId,
    Long tabletId,
    @JsonProperty("HH:mm:ss")
    LocalDateTime createdAt
) {
    @QueryProjection
    public PatientRequestResponse {}
}
