package com.sms.exception;

// Importing HTTP status codes (e.g., 404 Not Found, 400 Bad Request)
import org.springframework.http.HttpStatus;

// Represents the full HTTP response including status, headers, and body
import org.springframework.http.ResponseEntity;

// Exception thrown when validation on an argument annotated with @Valid fails
import org.springframework.web.bind.MethodArgumentNotValidException;

// Used to handle specific exceptions thrown in the application
import org.springframework.web.bind.annotation.ExceptionHandler;

// Makes this class a centralized exception handler for all REST controllers
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Utility classes for storing key-value pairs (used for error messages)
import java.util.HashMap;
import java.util.Map;

// This class will globally handle exceptions for all REST controllers
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles custom StudentNotFoundException thrown when a student is not found
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handleStudentNotFound(StudentNotFoundException ex) {
        // Return the exception message with 404 Not Found status
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handles validation errors when request body fails @Valid constraints
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        // Create a map to store field names and corresponding error messages
        Map<String, String> errors = new HashMap<>();
        
        // Loop through field errors and put each field and its error message into the map
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage()));
        
        // Return the map of validation errors with 400 Bad Request status
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
