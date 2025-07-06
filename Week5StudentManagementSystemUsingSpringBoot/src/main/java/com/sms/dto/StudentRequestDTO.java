package com.sms.dto;

// Ensures the annotated numeric field must be greater than or equal to a specified minimum value
import jakarta.validation.constraints.Min;

// Ensures the annotated string is not null and not blank (i.e., not just whitespace)
import jakarta.validation.constraints.NotBlank;

// Ensures the annotated string matches a given regular expression pattern
import jakarta.validation.constraints.Pattern;

// Lombok annotation to generate a constructor with all fields
import lombok.AllArgsConstructor;

// Lombok annotation to implement the Builder pattern for object creation
import lombok.Builder;

// Lombok annotation to automatically generate getters, setters, toString, equals, and hashCode
import lombok.Data;

// Lombok annotation to generate a no-argument constructor
import lombok.NoArgsConstructor;

// Lombok annotation to generate boilerplate code (getters, setters, etc.)
@Data

// Generates a constructor with all fields
@AllArgsConstructor

// Generates a no-args (default) constructor
@NoArgsConstructor

// Enables the use of the builder pattern for object creation
@Builder
public class StudentRequestDTO {

    // Validates that name is not null or blank; shows custom error if validation fails
    @NotBlank(message = "Name is required")
    private String name;

    // Validates that age must be at least 1 (positive number); custom error if not
    @Min(value = 1, message = "Age must be positive")
    private int age;

    // Validates that grade matches the allowed pattern (e.g., A+, B, C, etc.)
    @Pattern(regexp = "^(A\\+|A|B\\+|B|C\\+|C|-)$", message = "Invalid grade format")
    private String grade;

    // Address is optional; no validation constraint applied
    private String address;
}

