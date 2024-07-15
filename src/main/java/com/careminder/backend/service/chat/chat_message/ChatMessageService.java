package com.careminder.backend.service.chat.chat_message;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.chat.dto.chat_message.ChatMessageRequest;
import study.chat.dto.chat_message.SimpleChatMessageResponse;
import study.chat.implement.chat_message.ChatMessageAppender;
import study.chat.implement.chat_message.ChatMessageFinder;

import java.util.List;

@Service
public class ChatMessageService {

    private final ChatMessageAppender chatMessageAppender;
    private final ChatMessageFinder chatMessageFinder;

    public ChatMessageService(final ChatMessageAppender chatMessageAppender,
                              final ChatMessageFinder chatMessageFinder) {
        this.chatMessageAppender = chatMessageAppender;
        this.chatMessageFinder = chatMessageFinder;
    }

    @Transactional(readOnly = true)
    public void append(final ChatMessageRequest chatMessageRequest){
        // todo: 멤버 이름 찾는 로직
        chatMessageAppender.appendChat(chatMessageRequest);
    }

    @Transactional(readOnly = true)
    public List<SimpleChatMessageResponse> getAllByRoomId(final long roomId){
        return chatMessageFinder.getAllByRoomId(roomId).stream().map(SimpleChatMessageResponse::from).toList();
    }
}
