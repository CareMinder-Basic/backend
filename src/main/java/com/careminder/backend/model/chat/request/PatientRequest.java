package com.careminder.backend.model.chat.request;

import com.careminder.backend.global.annotation.Association;
import com.careminder.backend.global.base_entity.CreatedTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
public class PatientRequest extends CreatedTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_request_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;


    private String content;

    @Association(description = "Staff 외래키")
    private Long staffId;

    @Association(description = "Tablet 외래키")
    private Long tabletId;

    protected PatientRequest() {}

    @Builder
    public PatientRequest(final RequestStatus status, final String content, final Long staffId, final Long tabletId) {
        this.status = status;
        this.content = content;
        this.staffId = staffId;
        this.tabletId = tabletId;
    }
}
