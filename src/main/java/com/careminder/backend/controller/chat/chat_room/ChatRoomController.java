package com.careminder.backend.controller.chat.chat_room;

import com.careminder.backend.dto.chat.chat_room.ChatRoomAppendRequest;
import com.careminder.backend.dto.chat.chat_room.ChatRoomResponse;
import com.careminder.backend.dto.chat.subscription.SubscriptionRequest;
import com.careminder.backend.dto.chat.subscription.UnsubscribeRequest;
import com.careminder.backend.global.response.CollectionApiResponse;
import com.careminder.backend.service.chat.chat_room.ChatRoomService;
import com.careminder.backend.service.chat.subscription.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final SubscriptionService subscriptionService;

    public ChatRoomController(final ChatRoomService chatRoomService,
                              final SubscriptionService subscriptionService) {
        this.chatRoomService = chatRoomService;
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/chat-rooms")
    public CollectionApiResponse<ChatRoomResponse> findAllChatRoom(){
        return CollectionApiResponse.from(chatRoomService.getAll());
    }

    @PostMapping("/chat-rooms")
    public void appendChatRoom(@RequestBody final ChatRoomAppendRequest chatRoomAppendRequest){
        chatRoomService.append(chatRoomAppendRequest);
    }

    @GetMapping("/check-subscribe")
    public ResponseEntity<Boolean> checkSubscribe(@RequestParam final long memberId, @RequestParam final long roomId){
        return ResponseEntity.ok(subscriptionService.isSubscribed(memberId, roomId));
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody final SubscriptionRequest subscriptionRequest) {
        subscriptionService.subscribe(subscriptionRequest);
        return ResponseEntity.ok("채팅방 입장 완료.");
    }

    @DeleteMapping("/unsubscribe")
    public ResponseEntity<String> unsubscribe(@RequestBody final UnsubscribeRequest unsubscribeRequest) {
        subscriptionService.unsubscribe(unsubscribeRequest);
        return ResponseEntity.ok("채팅방 탈퇴 완료.");
    }
}