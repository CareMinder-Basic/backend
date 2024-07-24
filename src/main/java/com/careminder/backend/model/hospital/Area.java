package com.careminder.backend.model.hospital;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private Long id;

    /** 구역의 이름 */
    private String name;
    private Long wardId;

    protected Area() {
    }

    @Builder
    private Area(final String name, final long wardId) {
        this.name = name;
        this.wardId = wardId;
    }
}
