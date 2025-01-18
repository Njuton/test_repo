package com.example.myapp.exception;

/**
 * @author rmartynov
 * @since 14.01.2025
 */
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
