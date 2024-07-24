package com.careminder.backend.service.chat.chat_message;

import com.careminder.backend.dto.chat.chat_message.ChatMessageRequest;
import com.careminder.backend.dto.chat.chat_message.SimpleChatMessageResponse;
import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.implement.account.AuthManagerFactory;
import com.careminder.backend.implement.chat.chat_message.ChatMessageManager;
import com.careminder.backend.model.chat.chat_message.ChatMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChatMessageService {

    private final ChatMessageManager chatMessageManager;
    private final AuthManagerFactory authManagerFactory;

    public ChatMessageService(final ChatMessageManager chatMessageManager,
                              final AuthManagerFactory authManagerFactory) {
        this.chatMessageManager = chatMessageManager;
        this.authManagerFactory = authManagerFactory;
    }

    public ChatMessage append(final CustomUserDetails customUserDetails, final ChatMessageRequest chatMessageRequest) {
        return chatMessageManager.appendChat(customUserDetails, chatMessageRequest);
    }

    public List<SimpleChatMessageResponse> getAllByPatientRequestId(final long roomId) {
        return chatMessageManager.getAllByPatientRequestId(roomId).stream().map(SimpleChatMessageResponse::from).toList();
    }
}
