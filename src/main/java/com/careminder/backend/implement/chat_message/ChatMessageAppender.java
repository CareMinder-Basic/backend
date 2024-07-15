package com.careminder.backend.implement.chat_message;

import com.careminder.backend.dto.chat.chat_message.ChatJoinMessageRequest;
import com.careminder.backend.dto.chat.chat_message.ChatLeaveMessageRequest;
import com.careminder.backend.dto.chat.chat_message.ChatMessageRequest;
import com.careminder.backend.global.annotation.Implement;
import com.careminder.backend.repository.chat.chat_message.ChatMessageRepository;

@Implement
public class ChatMessageAppender {

    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageAppender(final ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public void appendJoinChat(final ChatJoinMessageRequest chatJoinMessageRequest){
        String sender = String.valueOf(chatJoinMessageRequest.memberId());
        chatMessageRepository.save(chatJoinMessageRequest.toEntity(sender));
    }

    public void appendChat(final ChatMessageRequest chatMessageRequest){
        String sender = String.valueOf(chatMessageRequest.memberId());
        chatMessageRepository.save(chatMessageRequest.toEntity(sender));
    }

    public void appendLeaveChat(final ChatLeaveMessageRequest chatLeaveMessageRequest){
        String sender = String.valueOf(chatLeaveMessageRequest.memberId());
        chatMessageRepository.save(chatLeaveMessageRequest.toEntity(sender));
    }

}
