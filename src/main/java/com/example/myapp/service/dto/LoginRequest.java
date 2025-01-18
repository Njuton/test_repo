package com.example.myapp.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * @author rmartynov
 * @since 14.01.2025
 */
@Schema(description = "User login request object")
public record LoginRequest(
        @NotNull(message = "User ID must not be null")
        @Schema(description = "Unique identifier of the user", example = "e4d2e6b0-cde2-42c5-aac3-0b8316f21e58")
        UUID id,

        @NotBlank(message = "Password must not be blank")
        @Schema(description = "Password for authentication", example = "Секретная строка")
        String password
) {
}