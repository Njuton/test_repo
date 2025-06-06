package com.example.myapp.rest;

import com.example.myapp.service.AuthService;
import com.example.myapp.service.dto.LoginRequest;
import com.example.myapp.service.dto.LoginResponse;
import com.example.myapp.utils.tx.TransactionRunner;
import com.example.myapp.utils.tx.TxMode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rmartynov
 * @since 14.01.2025
 */
@RestController
@RequestMapping
public class AuthController implements AuthApi {
    private final AuthService authService;
    private final TransactionRunner txRunner;

    public AuthController(AuthService authService, TransactionRunner txRunner) {
        this.authService = authService;
        this.txRunner = txRunner;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(new LoginResponse(txRunner.runInTransaction(() ->
                authService.login(loginRequest), TxMode.READ_ONLY)));
    }
}