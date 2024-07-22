package com.careminder.backend.global.config;

import com.careminder.backend.global.auth.JWTUtil;
import com.careminder.backend.global.interceptor.HttpHandshakeInterceptor;
import com.careminder.backend.global.interceptor.WebSocketInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;


@Order(Ordered.HIGHEST_PRECEDENCE + 99)
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final JWTUtil jwtUtil;
    private final WebSocketInterceptor webSocketInterceptor;

    public WebSocketConfig(final JWTUtil jwtUtil, final WebSocketInterceptor webSocketInterceptor) {
        this.jwtUtil = jwtUtil;
        this.webSocketInterceptor = webSocketInterceptor;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*");
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
        registry.setMessageSizeLimit(4 * 8192); // 32KB
        registry.setTimeToFirstMessage(30000);
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(webSocketInterceptor);
    }
}