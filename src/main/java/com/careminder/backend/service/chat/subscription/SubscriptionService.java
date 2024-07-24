package com.careminder.backend.service.chat.subscription;

import com.careminder.backend.dto.chat.chat_message.ChatJoinMessageRequest;
import com.careminder.backend.dto.chat.chat_message.ChatLeaveMessageRequest;
import com.careminder.backend.dto.chat.subscription.SubscriptionRequest;
import com.careminder.backend.dto.chat.subscription.UnsubscribeRequest;
import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.global.error.exception.BadRequestException;
import com.careminder.backend.implement.chat.chat_message.ChatMessageManager;
import com.careminder.backend.implement.chat.subscription.SubscriptionManager;
import com.careminder.backend.repository.chat.subscription.SubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscriptionService {

    private final SubscriptionManager subscriptionManager;
    private final ChatMessageManager chatMessageManager;

    public SubscriptionService(final SubscriptionManager subscriptionManager,
                               final ChatMessageManager chatMessageManager) {
        this.subscriptionManager = subscriptionManager;
        this.chatMessageManager = chatMessageManager;
    }

    public void subscribe(final CustomUserDetails customUserDetails, final SubscriptionRequest subscriptionRequest){
        long patientRequestId = subscriptionRequest.patientRequestId();
        if(isSubscribed(customUserDetails.getAccountId(), patientRequestId)){
            throw new BadRequestException("이미 구독한 채팅방");
        }
        subscriptionManager.save(subscriptionRequest.toEntity(customUserDetails));
    }

    public void unsubscribe(final CustomUserDetails customUserDetails, final UnsubscribeRequest unsubscribeRequest){
        long patientRequestId = unsubscribeRequest.patientRequestId();
        subscriptionManager.deleteByAccountIdAndPatientRequestId(customUserDetails.getAccountId(), patientRequestId);
    }

    public boolean isSubscribed(final long accountId, final long roomId){
        return subscriptionManager.existsByAccountIdAndPatientRequestId(accountId, roomId);
    }
}
