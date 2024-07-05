package com.careminder.backend.global.auth;

import com.careminder.backend.global.response.JWTResponse;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;

@Component
public class JWTUtil {

    private SecretKey secretKey;
    private static final Long accessTokenValidTime = Duration.ofDays(15).toMillis(); // 만료시간 15일
    private static final Long refreshTokenValidTime = Duration.ofDays(30).toMillis(); // 만료시간 30일

    public JWTUtil(@Value("${spring.jwt.secret}")String secret) {


        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public Long getUserId(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("userId", Long.class);
    }

    public String getUsername(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("username", String.class);
    }

    public String getRole(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public Boolean isExpired(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public JWTResponse createJWT(Long userId) {
        String accessToken = createToken(userId, accessTokenValidTime);
        String refreshToken = createToken(userId, refreshTokenValidTime);
        return JWTResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public String createAccessToken(Long userId) {
        return createToken(userId, accessTokenValidTime);
    }

    public String createRefreshToken(Long userId) {
        return createToken(userId, refreshTokenValidTime);
    }

    public String createToken(Long userId, Long expiredMs) {

        return Jwts.builder()
                .claim("userId", userId)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }
//    public String createToken(Long userId, String role, Long expiredMs) {
//
//        return Jwts.builder()
//                .claim("userId", userId)
//                .claim("role", role)
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(new Date(System.currentTimeMillis() + expiredMs))
//                .signWith(secretKey)
//                .compact();
//    }
}