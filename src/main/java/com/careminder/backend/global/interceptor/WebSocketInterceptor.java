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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
            List<String> authorization = accessor.getNativeHeader(JWTUtil.HEADER);
            if (authorization != null && !authorization.isEmpty()) {
                String token = authorization.getFirst().split(" ")[1];
                try {
                    Long accountId = jwtUtil.getAccountId(token);
                    String role = jwtUtil.getRole(token);

                    CustomUserDetails customUserDetails = new CustomUserDetails(accountId, convertRole(role));
                    Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    // WebSocket 세션에 Principal 객체 저장
                    accessor.setUser(authToken);
                } catch (JWTException e) {
                    log.error("JWT Verification Failed: " + e.getMessage());
                    return null;
                } catch (Exception e) {
                    log.error("An unexpected error occurred: " + e.getMessage());
                    return null;
                }
            } else {
                log.error("Authorization header is not found");
                return null;
            }
        }else{
            Principal user = accessor.getUser();
            if(user != null){
                System.out.println(user.getName());
            }else{
                System.out.println("user가 null");
            }
        }
        return message;
    }

    private Collection<GrantedAuthority> convertRole(final String role){
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}