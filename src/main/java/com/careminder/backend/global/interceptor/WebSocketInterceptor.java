package com.careminder.backend.global.interceptor;


import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.global.auth.JWTUtil;
import com.careminder.backend.global.error.exception.JWTException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.*;

@Slf4j
@Component
public class WebSocketInterceptor implements ChannelInterceptor {

    private final JWTUtil jwtUtil;

    public WebSocketInterceptor(final JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        // 연결 요청시 JWT 검증
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            log.info("Session ID (CONNECT): " + accessor.getSessionId());
            List<String> authorization = accessor.getNativeHeader(JWTUtil.HEADER);
            if (authorization != null && !authorization.isEmpty()) {
                String token = authorization.getFirst().split(" ")[1];
                try {
                    Authentication authentication = jwtUtil.getAuthentication(token);
                    accessor.setUser(authentication);
                    accessor.getSessionAttributes().put("user", authentication);
                    log.info("authToken 저장 완료");
                } catch (Exception e) {
                    log.error("An unexpected error occurred: " + e.getMessage());
                    return null;
                }
            } else {
                log.error("Authorization header is not found");
                return null;
            }
        } else {
            log.info("command : {}", accessor.getCommand());
        }
        return message;
    }
}