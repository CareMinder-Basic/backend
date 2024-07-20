package com.careminder.backend.global.interceptor;

import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.global.auth.JWTFilter;
import com.careminder.backend.global.auth.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class CustomHandshakeInterceptor implements HandshakeInterceptor {

    private final JWTUtil jwtUtil;

    public CustomHandshakeInterceptor(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        HttpServletRequest req = ((ServletServerHttpRequest)request).getServletRequest();
        HttpSession session  =  req.getSession(false);
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // "Bearer " 부분 제거
            try {
                CustomUserDetails customUserDetails = (CustomUserDetails) jwtUtil.getAuthentication(token);

                // WebSocket 세션에 유저 정보 저장
                attributes.put("user", customUserDetails);
                System.out.println("User authenticated: " + customUserDetails);
                return true;
            } catch (Exception e) {
                System.err.println("JWT parsing error: " + e.getMessage());
                return false;
            }
        } else {
            System.err.println("No JWT token found in request headers");
            return false;
        }
    }

    @Override
    public void afterHandshake(final ServerHttpRequest request, final ServerHttpResponse response, final WebSocketHandler wsHandler, final Exception exception) {

    }

    public static void printRequest(ServerHttpRequest request) {
        request.getHeaders().forEach((key, value) -> {
            System.out.println("Header Key: " + key + " - Header Value: " + value);
        });
    }
}
