package com.careminder.backend.repository.chat.chat_room;

import com.careminder.backend.model.chat.ChatRoom;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Repository
public class ChatRoomRepository {

    private final ChatRoomJpaRepository chatRoomJpaRepository;

    public ChatRoomRepository(ChatRoomJpaRepository chatRoomJpaRepository) {
        this.chatRoomJpaRepository = chatRoomJpaRepository;
    }

    public void save(final ChatRoom chatRoom){
        chatRoomJpaRepository.save(chatRoom);
    }

    public List<ChatRoom> getAll(){
        return chatRoomJpaRepository.findAll()
                .stream().sorted(Comparator.comparingLong(ChatRoom::getId)).toList();
    }

    public void delete(final long memberId, final long roomId){
        chatRoomJpaRepository.deleteByMemberIdAndId(memberId, roomId);
    }
}
