package com.example.myapp.rest;

import com.example.myapp.dao.entity.Message;
import com.example.myapp.service.RedisDialogService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

/**
 * @author rmartynov
 * @since 04.05.2025
 */
@RestController
@RequestMapping("/redis/dialog")
@ConditionalOnProperty(name = "redis.enabled", havingValue = "true")
public class RedisDialogController implements RedisDialogApi {

    private final RedisDialogService dialogService;

    public RedisDialogController(RedisDialogService dialogService) {
        this.dialogService = dialogService;
    }

    @PostMapping("/{user_id}/send")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> sendMessage(Principal principal, @PathVariable("user_id") UUID receiverId, @RequestBody String text) {
        UUID senderId = UUID.fromString(principal.getName());
        dialogService.sendMessage(senderId, receiverId, text);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{user_id}/list")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Message>> getDialog(Principal principal, @PathVariable("user_id") UUID friendId) {
        UUID userId = UUID.fromString(principal.getName());
        List<Message> dialog = dialogService.getDialog(userId, friendId);
        return ResponseEntity.ok(dialog);
    }
}
