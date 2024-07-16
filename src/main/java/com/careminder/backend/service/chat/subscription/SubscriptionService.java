package com.careminder.backend.service.chat.subscription;

import com.careminder.backend.dto.chat.chat_message.ChatJoinMessageRequest;
import com.careminder.backend.dto.chat.chat_message.ChatLeaveMessageRequest;
import com.careminder.backend.dto.chat.subscription.SubscriptionRequest;
import com.careminder.backend.dto.chat.subscription.UnsubscribeRequest;
import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.global.error.exception.BadRequestException;
import com.careminder.backend.implement.account.AccountMappingManager;
import com.careminder.backend.implement.chat.chat_message.ChatMessageManager;
import com.careminder.backend.implement.chat.subscription.SubscriptionManager;
import com.careminder.backend.repository.chat.subscription.SubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscriptionService {

    private final SubscriptionManager subscriptionManager;
    private final ChatMessageManager chatMessageManager;
    private final AccountMappingManager accountMappingManager;

    public SubscriptionService(final SubscriptionManager subscriptionManager,
                               final ChatMessageManager chatMessageManager,
                               final AccountMappingManager accountMappingManager) {
        this.subscriptionManager = subscriptionManager;
        this.chatMessageManager = chatMessageManager;
        this.accountMappingManager = accountMappingManager;
    }

    @Transactional
    public void subscribe(final CustomUserDetails customUserDetails, final SubscriptionRequest subscriptionRequest){
        long roomId = subscriptionRequest.roomId();
        Long accountMappingId = accountMappingManager.findOrCreateAccountMappingId(customUserDetails);
        if(isSubscribed(accountMappingId, roomId)){
            throw new BadRequestException("이미 구독한 채팅방");
        }
        subscriptionManager.save(subscriptionRequest.toEntity(accountMappingId));
        chatMessageManager.appendJoinChat(customUserDetails, new ChatJoinMessageRequest(roomId));
    }

    @Transactional
    public void unsubscribe(final CustomUserDetails customUserDetails, final UnsubscribeRequest unsubscribeRequest){
        long roomId = unsubscribeRequest.roomId();
        long accountMappingId = accountMappingManager.findOrCreateAccountMappingId(customUserDetails);
        subscriptionManager.deleteByAccountMappingIdAndRoomId(accountMappingId, roomId);
        chatMessageManager.appendLeaveChat(customUserDetails, new ChatLeaveMessageRequest(roomId));
    }

    @Transactional(readOnly = true)
    public boolean isSubscribed(final long accountMappingId, final long roomId){
        return subscriptionManager.existsByAccountMappingIdAndRoomId(accountMappingId, roomId);
    }
}
