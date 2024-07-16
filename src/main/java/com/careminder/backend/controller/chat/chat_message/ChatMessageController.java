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
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    public ChatMessageController(final ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/chat/{roomId}")
    public SimpleChatMessageResponse sendMessage(@CurrentUser final CustomUserDetails customUserDetails,
                                                 @DestinationVariable final long roomId,
                                                 final SimpleChatMessage scm) {
        // todo: 메시지 큐에 저장 + 일정 시간 이후 DB에 저장
        ChatMessageRequest chatMessageRequest = new ChatMessageRequest(roomId, scm.content());
        ChatMessage chatMessage = chatMessageService.append(customUserDetails, chatMessageRequest);
        return SimpleChatMessageResponse.from(chatMessage);
    }

    @ResponseBody
    @GetMapping("api/chat-history/{roomId}")
    public CollectionApiResponse<SimpleChatMessageResponse> getMessages(@PathVariable final long roomId){
        return CollectionApiResponse.from(chatMessageService.getAllByRoomId(roomId));
    }
}
