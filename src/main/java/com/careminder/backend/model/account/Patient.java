package com.careminder.backend.model.account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;
    private Gender gender;

    protected Patient() {}

    @Builder
    public Patient(String name, String phoneNumber, Gender gender) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }
}
