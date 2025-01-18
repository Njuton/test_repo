package com.example.myapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rmartynov
 * @since 14.01.2025
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // Обработка ошибок валидации (400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, errors.toString());
    }

    // Обработка ошибок конфликтов (409)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ProblemDetail handleIllegalArgumentException(UserAlreadyExistsException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    }

    // Обработка ошибок отсутствия объектов (404)
    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleNotFoundException(UserNotFoundException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // Обработка остальных ошибок (500)
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneralException(Exception ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
}