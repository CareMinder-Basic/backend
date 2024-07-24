package com.careminder.backend.model.account;

import com.careminder.backend.model.account.constant.Gender;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
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
