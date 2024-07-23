package com.careminder.backend.repository.account;

import com.careminder.backend.model.account.Tablet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TabletJpaRepository extends JpaRepository<Tablet,Long> {
}
