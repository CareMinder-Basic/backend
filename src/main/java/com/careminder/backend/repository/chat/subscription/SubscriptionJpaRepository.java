package com.careminder.backend.repository.chat.subscription;

import com.careminder.backend.model.chat.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionJpaRepository extends JpaRepository<Subscription, Long> {
    boolean existsByAccountMappingIdAndRoomId(final Long accountMappingId, final Long roomId);
    void deleteByAccountMappingIdAndRoomId(final Long accountMappingId, final Long roomId);
}
