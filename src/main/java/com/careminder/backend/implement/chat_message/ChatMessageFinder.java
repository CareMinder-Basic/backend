package com.careminder.backend.implement.chat_message;

import study.chat.entity.ChatMessage;
import study.chat.global.annotation.Implement;
import study.chat.repository.chat_message.ChatMessageRepository;

import java.util.List;

@Implement
public class ChatMessageFinder {

    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageFinder(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public List<ChatMessage> getAllByRoomId(final long roomId){
        return chatMessageRepository.getAllByRoomId(roomId);
    }
}
