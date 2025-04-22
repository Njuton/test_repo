package com.example.myapp.rest;

import com.example.myapp.exception.ExceptionCounter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rmartynov
 * @since 21.04.2025
 */
@RestController
@RequestMapping("/system")
public class SystemController {
    private final ExceptionCounter exceptionCounter;

    public SystemController(ExceptionCounter exceptionCounter) {
        this.exceptionCounter = exceptionCounter;
    }

    @GetMapping("/get-exception-count")
    public long getExceptionCount() {
        return exceptionCounter.getFailedRequests();
    }
}
