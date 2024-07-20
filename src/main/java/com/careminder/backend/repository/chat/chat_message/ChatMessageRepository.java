package com.careminder.backend.repository.chat.chat_message;

import com.careminder.backend.model.chat.ChatMessage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatMessageRepository {

    private final ChatMessageJpaRepository chatMessageJpaRepository;

    public ChatMessageRepository(final ChatMessageJpaRepository chatMessageJpaRepository) {
        this.chatMessageJpaRepository = chatMessageJpaRepository;
    }

    public void save(final ChatMessage chatMessage){
        chatMessageJpaRepository.save(chatMessage);
    }

    public List<ChatMessage> getAllByRoomId(final long roomId){
        return chatMessageJpaRepository.getAllByRoomId(roomId);
    }
}
