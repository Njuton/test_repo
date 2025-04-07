package com.example.myapp.rest;

import com.example.myapp.service.dto.RegisterRequest;
import com.example.myapp.service.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author rmartynov
 * @since 14.01.2025
 */
@Tag(name = "User Management", description = "Endpoints for user management")
public interface UserApi {

    @PostMapping("/register")
    @Operation(summary = "Register user", description = "Registers a new user in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Input data validation failed"),
            @ApiResponse(responseCode = "409", description = "User already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Map<String, String>> registerUser(@RequestBody RegisterRequest request);

    @GetMapping("/get/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieves a user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    UserDto getUserById(@PathVariable UUID id);

    @GetMapping("/get-all")
    @Operation(summary = "Get all users", description = "Retrieves all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    List<UserDto> getAllUsers();

    @GetMapping("/search")
    @Operation(
            summary = "Search users",
            description = "Searches for users whose first and last names start with the given prefixes"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Input data validation failed"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    List<UserDto> searchUsers(
            @RequestParam @Parameter(description = "Prefix of the first name") String firstNamePrefix,
            @RequestParam @Parameter(description = "Prefix of the last name") String lastNamePrefix
    );
}
