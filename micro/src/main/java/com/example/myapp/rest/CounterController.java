package com.example.myapp.rest;

import com.example.myapp.service.CounterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author rmartynov
 * @since 07.08.2025
 */
@RestController
@RequestMapping("/counters")
public class CounterController {

    private final CounterService counterService;

    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @PostMapping("/increment/{userId}")
    public ResponseEntity<Void> incrementCounter(@PathVariable UUID userId) {
        counterService.incrementCounter(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/decrement/{userId}")
    public ResponseEntity<Void> decrementCounter(@PathVariable UUID userId) {
        counterService.decrementCounter(userId);
        return ResponseEntity.ok().build();
    }
}