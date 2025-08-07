package com.example.myapp.rest;

import com.example.myapp.service.CounterService;
import com.example.myapp.utils.tx.TransactionRunner;
import com.example.myapp.utils.tx.TxMode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author rmartynov
 * @since 07.08.2025
 */
@RestController
@RequestMapping("/counters")
public class CounterController {

    private final CounterService counterService;
    private final TransactionRunner txRunner;

    public CounterController(CounterService counterService, TransactionRunner txRunner) {
        this.counterService = counterService;
        this.txRunner = txRunner;
    }

    @PostMapping("/increment/{userId}")
    public ResponseEntity<Void> incrementCounter(@PathVariable UUID userId) {
        txRunner.runInTransaction(() -> counterService.incrementCounter(userId), TxMode.CURRENT_OR_NEW);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/decrement/{userId}/{count}")
    public ResponseEntity<Void> decrementCounter(@PathVariable UUID userId, @PathVariable int count) {
        txRunner.runInTransaction(() -> counterService.decrementCounter(userId, count), TxMode.CURRENT_OR_NEW);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<Integer> getCounter(@PathVariable UUID userId) {
        int counter = txRunner.runInTransaction(() -> counterService.getCounter(userId), TxMode.READ_ONLY);
        return ResponseEntity.ok(counter);
    }
}