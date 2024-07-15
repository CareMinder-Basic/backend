package com.careminder.backend.repository.chat.subscription;

import com.careminder.backend.model.chat.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionJpaRepository extends JpaRepository<Subscription, Long> {
    boolean existsByMemberIdAndRoomId(final long memberId, final long roomId);
    void deleteByMemberIdAndRoomId(final long memberId, final long roomId);
}
