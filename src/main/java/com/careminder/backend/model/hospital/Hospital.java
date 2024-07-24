package com.careminder.backend.model.hospital;

import jakarta.persistence.*;

@Entity
public class Hospital {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id")
    private Long id;

    /** 병원의 이름 */
    private String name;
    private String address;
    private String businessRegistrationNumber;
}
