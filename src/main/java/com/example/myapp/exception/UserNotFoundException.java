package com.example.myapp.exception;

/**
 * @author rmartynov
 * @since 14.01.2025
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
