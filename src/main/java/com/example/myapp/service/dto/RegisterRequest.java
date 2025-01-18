package com.example.myapp.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * @author rmartynov
 * @since 14.01.2025
 */
@Schema(description = "User registration request object")
public record RegisterRequest(
        @NotBlank(message = "First name must not be blank")
        @Schema(description = "First name of the user", example = "John")
        String firstName,

        @NotBlank(message = "Last name must not be blank")
        @Schema(description = "Last name of the user", example = "Doe")
        String lastName,

        @NotNull(message = "Birthdate must not be null")
        @Past(message = "Birthdate must be a date in the past")
        @Schema(description = "Birthdate of the user in the past", example = "1990-01-01", format = "date")
        LocalDate birthdate,

        @Size(max = 500, message = "Biography must not exceed 500 characters")
        @Schema(description = "Short biography of the user", example = "I am a software developer from NY.")
        String biography,

        @NotBlank(message = "City must not be blank")
        @Schema(description = "City of residence of the user", example = "New York")
        String city,

        @NotBlank(message = "Username must not be blank")
        @Schema(description = "Unique username for the user", example = "johndoe123")
        String username,

        @NotBlank(message = "Password must not be blank")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        @Schema(description = "Password for the user account", example = "password123", minLength = 6)
        String password
) {
}
