package com.careminder.backend.service.chat.chat_room;

import com.careminder.backend.dto.chat.chat_room.ChatRoomAppendRequest;
import com.careminder.backend.dto.chat.chat_room.ChatRoomResponse;
import com.careminder.backend.implement.chat_message.ChatMessageAppender;
import com.careminder.backend.model.chat.ChatRoom;
import com.careminder.backend.repository.chat.chat_room.ChatRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageAppender chatMessageAppender;

    public ChatRoomService(final ChatRoomRepository chatRoomRepository,
                           final ChatMessageAppender chatMessageAppender) {
        this.chatRoomRepository = chatRoomRepository;
        this.chatMessageAppender = chatMessageAppender;
    }

    @Transactional(readOnly = true)
    public List<ChatRoomResponse> getAll() {
        return chatRoomRepository.getAll().stream()
                .map(ChatRoomResponse::from).toList();
    }

    @Transactional
    public void append(final ChatRoomAppendRequest chatRoomAppendRequest) {
        ChatRoom chatRoom = chatRoomAppendRequest.toEntity();
        chatRoomRepository.save(chatRoom);
    }
}
