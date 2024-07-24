package com.careminder.backend.repository.chat.chat_message;

import com.careminder.backend.model.chat.chat_message.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageJpaRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> getAllByPatientRequestId(final Long patientRequestId);
}
