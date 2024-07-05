package com.careminder.backend.model.hospital;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Hospital {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 병원의 이름 */
    private String name;
    private String address;
    private String businessRegistrationNumber;
}
