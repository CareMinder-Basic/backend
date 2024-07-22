package com.careminder.backend.global.auth;

import com.careminder.backend.global.error.exception.JWTException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JWTException exception) {
            setErrorResponse(response, exception);
        }
    }

    public void setErrorResponse(final HttpServletResponse response, final JWTException exception)
            throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        final Map<String, Object> body = new HashMap<>();
        body.put("code", exception.statusCode());
        body.put("message", exception.message());

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
}