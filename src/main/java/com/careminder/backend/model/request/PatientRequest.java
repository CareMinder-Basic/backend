package com.careminder.backend.model.request;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PatientRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private String content;
    private LocalDateTime requestTime;
    private Long staffId;
    private Long tabletId;


}
