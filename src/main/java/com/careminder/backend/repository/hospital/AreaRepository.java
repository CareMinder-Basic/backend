package com.careminder.backend.repository.hospital;

import com.careminder.backend.model.hospital.Area;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<Area, Long> {
}
