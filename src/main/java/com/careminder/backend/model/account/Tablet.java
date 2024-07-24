package com.careminder.backend.model.account;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Entity
@Getter
public class Tablet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tablet_id")
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
