package com.careminder.backend.implement.chat.subscription;

import com.careminder.backend.global.annotation.Implement;
import com.careminder.backend.model.chat.subscription.Subscription;
import com.careminder.backend.repository.chat.subscription.SubscriptionRepository;
import org.springframework.transaction.annotation.Transactional;

@Implement
public class SubscriptionManager {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionManager(final SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Transactional
    public void save(final Subscription subscription){
        subscriptionRepository.save(subscription);
    }

    @Transactional(readOnly = true)
    public boolean existsByAccountIdAndPatientRequestId(final Long accountId, final Long patientRequestId){
        return subscriptionRepository.existsByAccountIdAndPatientRequestId(accountId, patientRequestId);
    }

    @Transactional
    public void deleteByAccountIdAndPatientRequestId(final Long accountId, final Long patientRequestId){
        subscriptionRepository.deleteByAccountIdAndPatientRequestId(accountId, patientRequestId);
    }
}
