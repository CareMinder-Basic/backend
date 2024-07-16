package com.careminder.backend.service.chat.chat_room;

import com.careminder.backend.dto.chat.chat_room.ChatRoomAppendRequest;
import com.careminder.backend.dto.chat.chat_room.ChatRoomResponse;
import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.implement.account.AccountMappingManager;
import com.careminder.backend.implement.chat.chat_message.ChatMessageManager;
import com.careminder.backend.model.chat.ChatRoom;
import com.careminder.backend.repository.chat.chat_room.ChatRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final AccountMappingManager accountMappingManager;

    public ChatRoomService(final ChatRoomRepository chatRoomRepository,
                           final AccountMappingManager accountMappingManager) {
        this.chatRoomRepository = chatRoomRepository;
        this.accountMappingManager = accountMappingManager;
    }

    @Transactional(readOnly = true)
    public List<ChatRoomResponse> getAll() {
        return chatRoomRepository.getAll().stream()
                .map(ChatRoomResponse::from).toList();
    }

    @Transactional
    public void append(final CustomUserDetails customUserDetails, final ChatRoomAppendRequest chatRoomAppendRequest) {
        Long accountMappingId = accountMappingManager.findOrCreateAccountMappingId(customUserDetails);
        ChatRoom chatRoom = chatRoomAppendRequest.toEntity(accountMappingId);
        chatRoomRepository.save(chatRoom);
    }
}
