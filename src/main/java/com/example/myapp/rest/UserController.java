package com.example.myapp.rest;

import com.example.myapp.service.UserService;
import com.example.myapp.service.dto.RegisterRequest;
import com.example.myapp.service.dto.UserDto;
import com.example.myapp.utils.tx.TransactionRunner;
import com.example.myapp.utils.tx.TxMode;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author rmartynov
 * @since 14.01.2025
 */
@RestController
@RequestMapping("/user")
public class UserController implements UserApi {
    private final UserService userService;
    private final TransactionRunner txRunner;

    public UserController(UserService userService, TransactionRunner txRunner) {
        this.userService = userService;
        this.txRunner = txRunner;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@Valid @RequestBody RegisterRequest request) {
        UUID userId = txRunner.runInTransaction(() -> userService.registerUser(request), TxMode.CURRENT_OR_NEW, null);
        Map<String, String> response = new HashMap<>();
        response.put("user_id", userId.toString());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public UserDto getUserById(@PathVariable UUID id) {
        return txRunner.runInTransaction(() -> userService.getUserById(id), TxMode.READ_ONLY, null);
    }

    @GetMapping("/get-all")
    public List<UserDto> getAllUsers() {
        return txRunner.runInTransaction(userService::getAllUsers, TxMode.READ_ONLY, null);
    }

    @GetMapping("/search")
    public List<UserDto> searchUsers(
            @RequestParam String firstNamePrefix,
            @RequestParam String lastNamePrefix) {
        return txRunner.runInTransaction(() -> userService.search(firstNamePrefix, lastNamePrefix), TxMode.READ_ONLY, null);
    }
}
