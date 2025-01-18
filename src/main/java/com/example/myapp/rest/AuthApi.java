package com.example.myapp.rest;

import com.example.myapp.service.dto.LoginRequest;
import com.example.myapp.service.dto.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author rmartynov
 * @since 14.01.2025
 */
@Tag(name = "Authentication", description = "Endpoints for user authentication")
public interface AuthApi {

    @PostMapping("/login")
    @Operation(summary = "Authenticate user", description = "Authenticates a user and returns a JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful authentication"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest);

}
