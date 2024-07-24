package com.careminder.backend.repository.chat.subscription;

import com.careminder.backend.model.chat.subscription.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionJpaRepository extends JpaRepository<Subscription, Long> {

    boolean existsByAccountIdAndPatientRequestId(final Long accountId, final Long patientRequestId);

    void deleteByAccountIdAndPatientRequestId(final Long accountId, final Long patientRequestId);
}
