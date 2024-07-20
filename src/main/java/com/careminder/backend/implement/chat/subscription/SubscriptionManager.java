package com.careminder.backend.implement.chat.subscription;

import com.careminder.backend.global.annotation.Implement;
import com.careminder.backend.implement.account.AccountMappingManager;
import com.careminder.backend.implement.account.AuthManagerFactory;
import com.careminder.backend.model.chat.Subscription;
import com.careminder.backend.repository.chat.subscription.SubscriptionRepository;

@Implement
public class SubscriptionManager {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionManager(final SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public void save(final Subscription subscription){
        subscriptionRepository.save(subscription);
    }

    public boolean existsByAccountMappingIdAndRoomId(final long accountMappingId, final long roomId){
        return subscriptionRepository.existsByAccountMappingIdAndRoomId(accountMappingId, roomId);
    }

    public void deleteByAccountMappingIdAndRoomId(final long accountMappingId, final long roomId){
        subscriptionRepository.deleteByAccountMappingIdAndRoomId(accountMappingId, roomId);
    }
}
