package com.example.myapp.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author rmartynov
 * @since 14.01.2025
 */
@Schema(description = "User login response object")
public record LoginResponse(
        @Schema(description = "JWT token for authentication", example = "e4d2e6b0-cde2-42c5-aac3-0b8316f21e58")
        String token
) {
}
