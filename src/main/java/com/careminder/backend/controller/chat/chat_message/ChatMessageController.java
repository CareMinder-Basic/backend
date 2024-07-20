package com.careminder.backend.controller.chat.chat_message;

import com.careminder.backend.dto.chat.chat_message.ChatMessageRequest;
import com.careminder.backend.dto.chat.chat_message.SimpleChatMessage;
import com.careminder.backend.dto.chat.chat_message.SimpleChatMessageResponse;
import com.careminder.backend.global.annotation.CurrentUser;
import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.global.response.CollectionApiResponse;
import com.careminder.backend.model.chat.ChatMessage;
import com.careminder.backend.service.chat.chat_message.ChatMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Slf4j
@Controller
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    public ChatMessageController(final ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/chat/{roomId}")
    public SimpleChatMessageResponse sendMessage(SimpMessageHeaderAccessor headerAccessor,
                                                 @DestinationVariable final long roomId, @Payload final SimpleChatMessage scm) {
        // WebSocket 세션에서 유저 정보 가져오기
        CustomUserDetails customUserDetails = (CustomUserDetails) headerAccessor.getSessionAttributes().get("user");

        // 세션 ID 출력
        String sessionId = headerAccessor.getSessionId();
        System.out.println("Session ID: " + sessionId);
        log.info("Session ID: {}", sessionId);

        // Principal 정보 출력
        if (customUserDetails != null) {
            log.info("User ID: {}", customUserDetails.getUsername());
            log.info("Role: {}", customUserDetails.getAuthorities().toString());
        } else {
            log.warn("User details are null");
        }

        // 메시지 내용 출력
        log.info("Room ID: {}", roomId);
        log.info("Message Content: {}", scm.content());

        // 메시지 큐에 저장 + 일정 시간 이후 DB에 저장
        ChatMessageRequest chatMessageRequest = new ChatMessageRequest(roomId, scm.content());
        ChatMessage chatMessage = chatMessageService.append(customUserDetails, chatMessageRequest);
        return SimpleChatMessageResponse.from(chatMessage);
    }

    @ResponseBody
    @GetMapping("/api/chat-history/{roomId}")
    public CollectionApiResponse<SimpleChatMessageResponse> getMessages(@PathVariable final long roomId){
        return CollectionApiResponse.from(chatMessageService.getAllByRoomId(roomId));
    }
}
