package com.sms.dto;

// Lombok annotation to generate getters, setters, toString, equals, and hashCode methods automatically
import lombok.Data;

// Lombok annotation to generate a constructor with all arguments
import lombok.AllArgsConstructor;

// Lombok annotation to implement the Builder design pattern (used to build object instances)
import lombok.Builder;

// Lombok annotation to generate a no-argument constructor
import lombok.NoArgsConstructor;

// Lombok annotation to generate boilerplate code like getters/setters, toString, equals, and hashCode
@Data

// Generates a constructor with all declared fields as parameters (id, name, age, grade, address)
@AllArgsConstructor

// Generates a no-argument (default) constructor
@NoArgsConstructor

// Enables the builder pattern for creating objects in a readable way
@Builder
public class StudentResponseDTO {

    // Unique identifier for the student
    private Long id;

    // Name of the student
    private String name;

    // Age of the student
    private int age;

    // Grade/class the student is in
    private String grade;

    // Address of the student
    private String address;
}
