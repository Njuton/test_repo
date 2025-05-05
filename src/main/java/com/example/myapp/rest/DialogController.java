package com.example.myapp.rest;

import com.example.myapp.config.TransactionContextHolder;
import com.example.myapp.service.DialogService;
import com.example.myapp.dao.entity.Message;
import com.example.myapp.utils.tx.TransactionRunner;
import com.example.myapp.utils.tx.TxMode;
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
@RequestMapping("/dialog")
public class DialogController implements DialogApi {

    private final DialogService dialogService;
    private final TransactionRunner txRunner;

    public DialogController(DialogService dialogService, TransactionRunner txRunner) {
        this.dialogService = dialogService;
        this.txRunner = txRunner;
    }

    @PostMapping("/{user_id}/send")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> sendMessage(Principal principal, @PathVariable("user_id") UUID receiverId, @RequestBody String text) {
        UUID senderId = UUID.fromString(principal.getName());
        txRunner.runInTransaction(() -> dialogService.sendMessage(senderId, receiverId, text), TxMode.CURRENT_OR_NEW,
                TransactionContextHolder.TransactionType.SHARDING);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{user_id}/list")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Message>> getDialog(Principal principal, @PathVariable("user_id") UUID friendId) {
        UUID userId = UUID.fromString(principal.getName());
        List<Message> dialog = txRunner.runInTransaction(() -> dialogService.getDialog(userId, friendId), TxMode.READ_ONLY,
                TransactionContextHolder.TransactionType.SHARDING);
        return ResponseEntity.ok(dialog);
    }
}
