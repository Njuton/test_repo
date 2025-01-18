package com.example.myapp.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author rmartynov
 * @since 14.01.2025
 */
@Schema(description = "User Data Transfer Object")
public record UserDto(
        @Schema(description = "Unique identifier of the user", example = "e4d2e6b0-cde2-42c5-aac3-0b8316f21e58")
        UUID id,

        @Schema(description = "Unique username of the user", example = "johndoe123")
        String username,

        @Schema(description = "First name of the user", example = "John")
        String firstName,

        @Schema(description = "Last name of the user", example = "Doe")
        String lastName,

        @Schema(description = "Birthdate of the user", example = "1990-01-01", format = "date")
        LocalDate birthdate,

        @Schema(description = "Short biography of the user", example = "Software engineer with 10 years of experience.")
        String biography,

        @Schema(description = "City of residence of the user", example = "New York")
        String city
) {
}
