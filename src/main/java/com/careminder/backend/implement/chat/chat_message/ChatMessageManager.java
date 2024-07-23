package com.careminder.backend.implement.chat.chat_message;

import com.careminder.backend.dto.chat.chat_message.ChatMessageRequest;
import com.careminder.backend.global.annotation.Implement;
import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.implement.account.AuthManagerFactory;
import com.careminder.backend.implement.account.BaseAuthManager;
import com.careminder.backend.model.account.Role;
import com.careminder.backend.model.chat.ChatMessage;
import com.careminder.backend.repository.chat.chat_message.ChatMessageRepository;

import java.util.List;

@Implement
public class ChatMessageManager {

    private final ChatMessageRepository chatMessageRepository;
    private final AuthManagerFactory authManagerFactory;

    public ChatMessageManager(final ChatMessageRepository chatMessageRepository,
                              final AuthManagerFactory authManagerFactory) {
        this.chatMessageRepository = chatMessageRepository;
        this.authManagerFactory = authManagerFactory;
    }

    public ChatMessage appendChat(final CustomUserDetails customUserDetails, final ChatMessageRequest chatMessageRequest){
        String senderName = getSenderName(customUserDetails);
        ChatMessage chatMessage = chatMessageRequest.toEntity(customUserDetails, senderName);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> getAllByRoomId(final long roomId){
        return chatMessageRepository.getAllByRoomId(roomId);
    }

    private String getSenderName(final CustomUserDetails customUserDetails){
        Role role = customUserDetails.getRole();
        Long accountId = customUserDetails.getAccountId();
        BaseAuthManager baseAuthManager = authManagerFactory.getAuthManager(role);
        return baseAuthManager.getName(accountId);
    }
}
