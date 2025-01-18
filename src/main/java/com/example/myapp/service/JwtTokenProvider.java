package com.example.myapp.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
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
                .setClaims(Map.of("username", username)) // Добавляем данные в payload
                .setSubject(userId.toString()) // Уникальный идентификатор пользователя
                .setIssuedAt(new Date()) // Время создания токена
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)) // Время истечения токена
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // Подпись токена
                .compact();
    }
}