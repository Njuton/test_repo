package com.example.myapp.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author rmartynov
 * @since 14.01.2025
 */
@Component
public class JwtTokenProvider {
    private final String jwtSecret;
    private final long jwtExpirationMs = TimeUnit.DAYS.toMillis(7);

    public JwtTokenProvider() throws IOException {
        ClassPathResource resource = new ClassPathResource("jwt-secret.txt");
        try (InputStream inputStream = resource.getInputStream()) {
            jwtSecret = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    public String generateToken(UUID userId, String username) {
        return Jwts.builder()
                .setClaims(Map.of("userId", userId))
                .setSubject(userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}