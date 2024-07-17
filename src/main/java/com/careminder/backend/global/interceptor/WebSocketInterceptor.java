package com.careminder.backend.global.interceptor;


import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.global.auth.JWTExceptionFilter;
import com.careminder.backend.global.auth.JWTUtil;
import com.careminder.backend.global.error.exception.JWTException;
import lombok.RequiredArgsConstructor;
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
            // Authorization 헤더 추출
            List<String> authorization = accessor.getNativeHeader(JWTUtil.HEADER);
            if (authorization != null && !authorization.isEmpty()) {
                String token = authorization.getFirst().split(" ")[1];
                try {
                    //토큰에서 username과 role 획득
                    Long accountId = jwtUtil.getAccountId(token);
                    String role = jwtUtil.getRole(token);

                    //UserDetails에 회원 정보 객체 담기
                    CustomUserDetails customUserDetails = new CustomUserDetails(accountId, convertRole(role));

                    //스프링 시큐리티 인증 토큰 생성
                    Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

                    //세션에 사용자 등록
                    SecurityContextHolder.getContext().setAuthentication(authToken);
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
        }
        return message;
    }

    private Collection<GrantedAuthority> convertRole(final String role){
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}