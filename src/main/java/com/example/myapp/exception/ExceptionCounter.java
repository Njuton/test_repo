package com.example.myapp.exception;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author rmartynov
 * @since 21.04.2025
 */
@Component
public class ExceptionCounter {
    private final AtomicLong counter = new AtomicLong();

    public void increment() {
        counter.incrementAndGet();
    }

    public long getFailedRequests() {
        return counter.get();
    }
}