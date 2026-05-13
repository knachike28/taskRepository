package com.taskManagement.exception;

// Defines package location for exception handling classes

import org.springframework.http.HttpStatus;
// Provides HTTP status codes like 404, 400, 500

import org.springframework.http.ResponseEntity;
// Used for returning custom HTTP responses

import org.springframework.web.bind.MethodArgumentNotValidException;
// Exception triggered when validation fails

import org.springframework.web.bind.annotation.ControllerAdvice;
// Enables global exception handling across application

import org.springframework.web.bind.annotation.ExceptionHandler;
// Used to define methods that handle exceptions

import java.util.HashMap;
import java.util.Map;
// Used for storing validation error messages

@ControllerAdvice
// Makes this class global exception handler
// Handles exceptions from all controllers

public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    // Handles TaskNotFoundException specifically

    public ResponseEntity<String> handleExpenseNotFound(
            TaskNotFoundException ex) {

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.NOT_FOUND);

        // Returns:
        // Error message
        // HTTP 404 NOT FOUND
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    // Handles validation-related exceptions

    public ResponseEntity<Map<String, String>>
    handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        // Stores field-wise validation errors

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()));

        // Extracts validation errors
        // Example:
        // title -> Title is required

        return new ResponseEntity<>(
                errors,
                HttpStatus.BAD_REQUEST);

        // Returns validation errors with HTTP 400
    }

    @ExceptionHandler(Exception.class)
    // Handles all generic/unhandled exceptions

    public ResponseEntity<String> handleGenericException(
            Exception ex) {

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);

        // Returns generic error message
        // HTTP 500 INTERNAL SERVER ERROR
    }
}