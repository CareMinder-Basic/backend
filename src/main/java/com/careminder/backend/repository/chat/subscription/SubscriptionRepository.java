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
    
    public boolean existsByAccountMappingIdAndRoomId(final long accountMappingId, final long roomId){
        return subscriptionJpaRepository.existsByAccountMappingIdAndRoomId(accountMappingId, roomId);
    }

    public void deleteByAccountMappingIdAndRoomId(final long accountMappingId, final long roomId){
        subscriptionJpaRepository.deleteByAccountMappingIdAndRoomId(accountMappingId, roomId);
    }
}
