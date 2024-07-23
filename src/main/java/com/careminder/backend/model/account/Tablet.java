package com.careminder.backend.model.account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Tablet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tabletName;
    private Long wardId;
    private Long areaId;
    private Long patientId;

    protected Tablet() {
    }

    @Builder
    public Tablet(String tabletName, Long wardId, Long areaId, Long patientId) {
        this.tabletName = tabletName;
        this.wardId = wardId;
        this.areaId = areaId;
        this.patientId = patientId;
    }

    public void updatePatientId(final Long patientId) {
        this.patientId = patientId;
    }
}
