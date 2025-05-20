package com.example.myapp.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author rmartynov
 * @since 14.01.2025
 */
@Schema(description = "Post Data Transfer Object")
public record PostDto(
        @Schema
        UUID id,

        @Schema
        UUID userId,

        @Schema
        String text,

        @Schema
        LocalDateTime createdAt
) implements Serializable {
}
