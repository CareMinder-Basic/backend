package com.careminder.backend.dto.chat.request;

import com.careminder.backend.model.chat.request.RequestStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

public record PatientRequestResponse(
    Long id,
    RequestStatus requestStatus,
    String content,
    Long staffId,
    Long tabletId,
    LocalDateTime createdAt
) {
    @QueryProjection
    public PatientRequestResponse {}
}
