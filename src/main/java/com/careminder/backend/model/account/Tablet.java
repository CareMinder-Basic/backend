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
    private Long areaId;

    protected Tablet() {
    }

    @Builder
    public Tablet(String tabletName, Long areaId) {
        this.tabletName = tabletName;
        this.areaId = areaId;
    }
}
