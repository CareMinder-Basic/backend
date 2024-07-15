package com.careminder.backend.service.chat.subscription;

import com.careminder.backend.dto.chat.chat_message.ChatJoinMessageRequest;
import com.careminder.backend.dto.chat.chat_message.ChatLeaveMessageRequest;
import com.careminder.backend.dto.chat.subscription.SubscriptionRequest;
import com.careminder.backend.dto.chat.subscription.UnsubscribeRequest;
import com.careminder.backend.global.error.exception.BadRequestException;
import com.careminder.backend.implement.chat_message.ChatMessageAppender;
import com.careminder.backend.repository.chat.subscription.SubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final ChatMessageAppender chatMessageAppender;

    public SubscriptionService(final SubscriptionRepository subscriptionRepository,
                               final ChatMessageAppender chatMessageAppender) {
        this.subscriptionRepository = subscriptionRepository;
        this.chatMessageAppender = chatMessageAppender;
    }

    @Transactional
    public void subscribe(final SubscriptionRequest subscriptionRequest){
        final long roomId = subscriptionRequest.roomId();
        final long memberId = subscriptionRequest.memberId();
        if(isSubscribed(memberId, roomId)){
            throw new BadRequestException("이미 구독한 채팅방");
        }
        subscriptionRepository.save(subscriptionRequest.toEntity());
        chatMessageAppender.appendJoinChat(ChatJoinMessageRequest.from(subscriptionRequest));
    }

    @Transactional
    public void unsubscribe(final UnsubscribeRequest unsubscribeRequest){
        final long roomId = unsubscribeRequest.roomId();
        final long memberId = unsubscribeRequest.memberId();
        subscriptionRepository.deleteByMemberIdAndRoomId(memberId, roomId);
        chatMessageAppender.appendLeaveChat(ChatLeaveMessageRequest.from(unsubscribeRequest));
    }

    @Transactional(readOnly = true)
    public boolean isSubscribed(final long memberId, final long roomId){
        return subscriptionRepository.existsByMemberIdAndRoomId(memberId, roomId);
    }
}
