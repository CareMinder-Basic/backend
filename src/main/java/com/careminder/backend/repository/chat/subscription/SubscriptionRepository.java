package com.careminder.backend.repository.chat.subscription;

import com.careminder.backend.model.chat.Subscription;
import org.springframework.stereotype.Repository;

@Repository
public class SubscriptionRepository {

    private final SubscriptionJpaRepository subscriptionJpaRepository;

    public SubscriptionRepository(final SubscriptionJpaRepository subscriptionJpaRepository) {
        this.subscriptionJpaRepository = subscriptionJpaRepository;
    }

    public void save(final Subscription subscription){
        subscriptionJpaRepository.save(subscription);
    }
    
    public boolean existsByAccountIdAndPatientRequestId(final Long accountId, final Long patientRequestId){
        return subscriptionJpaRepository.existsByAccountIdAndPatientRequestId(accountId, patientRequestId);
    }

    public void deleteByAccountIdAndPatientRequestId(final Long accountId, final Long patientRequestId){
        subscriptionJpaRepository.deleteByAccountIdAndPatientRequestId(accountId, patientRequestId);
    }
}
