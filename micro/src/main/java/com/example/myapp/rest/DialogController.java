package com.example.myapp.rest;

import com.example.myapp.dao.entity.Message;
import com.example.myapp.service.DialogService;
import com.example.myapp.utils.tx.TransactionRunner;
import com.example.myapp.utils.tx.TxMode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/from/{sender_id}/to/{receiver_id}/send")
    public ResponseEntity<Void> sendMessage(@PathVariable("sender_id") UUID senderId,
                                            @PathVariable("receiver_id") UUID receiverId, @RequestBody String text) {
        txRunner.runInTransaction(() -> dialogService.sendMessage(senderId, receiverId, text), TxMode.CURRENT_OR_NEW);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{user_id}/{friend_id}/list")
    public ResponseEntity<List<Message>> getDialog(@PathVariable("user_id") UUID userId, @PathVariable("friend_id") UUID friendId) {
        List<Message> dialog = txRunner.runInTransaction(() -> dialogService.getDialog(userId, friendId), TxMode.READ_ONLY);
        return ResponseEntity.ok(dialog);
    }
}
