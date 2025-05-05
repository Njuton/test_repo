package com.example.myapp.rest;

import com.example.myapp.service.UserService;
import com.example.myapp.service.dto.UserDto;
import com.example.myapp.utils.tx.TransactionRunner;
import com.example.myapp.utils.tx.TxMode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/friend")
public class FriendController implements FriendApi {

    private final UserService userService;
    private final TransactionRunner txRunner;

    public FriendController(UserService userService, TransactionRunner txRunner) {
        this.userService = userService;
        this.txRunner = txRunner;
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/set/{user_id}")
    public ResponseEntity<String> addFriend(Principal principal, @PathVariable("user_id") UUID friendId) {
        UUID userId = UUID.fromString(principal.getName());
        txRunner.runInTransaction(() -> userService.addFriend(userId, friendId), TxMode.CURRENT_OR_NEW, null);
        return ResponseEntity.ok().build();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/delete/{user_id}")
    public ResponseEntity<String> removeFriend(Principal principal, @PathVariable("user_id") UUID friendId) {
        UUID userId = UUID.fromString(principal.getName());
        txRunner.runInTransaction(() -> userService.removeFriend(userId, friendId), TxMode.CURRENT_OR_NEW, null);
        return ResponseEntity.ok().build();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/get-all")
    public List<UserDto> list(Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        return txRunner.runInTransaction(() -> userService.getFriends(userId), TxMode.READ_ONLY, null);
    }
}
