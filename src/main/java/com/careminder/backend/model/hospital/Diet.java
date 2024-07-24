package com.careminder.backend.model.hospital;

import jakarta.persistence.*;

@Entity
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diet_id")
    private Long id;
}
