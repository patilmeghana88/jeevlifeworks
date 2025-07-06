package com.sms.controller;


// Importing the List interface for handling lists of students
import java.util.List;

// Represents a full HTTP response, including status code and body
import org.springframework.http.ResponseEntity;

// Used to handle DELETE requests in REST APIs
import org.springframework.web.bind.annotation.DeleteMapping;

// Used to handle GET requests in REST APIs
import org.springframework.web.bind.annotation.GetMapping;

// Used to extract values from the URI path (e.g., /students/{id})
import org.springframework.web.bind.annotation.PathVariable;

// Used to handle POST requests in REST APIs
import org.springframework.web.bind.annotation.PostMapping;

// Used to handle PUT requests in REST APIs
import org.springframework.web.bind.annotation.PutMapping;

// Indicates that a method parameter should be bound to the body of the HTTP request
import org.springframework.web.bind.annotation.RequestBody;

// Declares the base path for all endpoints in this controller
import org.springframework.web.bind.annotation.RequestMapping;

// Marks this class as a REST controller that returns JSON responses
import org.springframework.web.bind.annotation.RestController;

// DTO for accepting student creation/update data from the client
import com.sms.dto.StudentRequestDTO;

// DTO for sending student data to the client
import com.sms.dto.StudentResponseDTO;

// Interface containing the business logic for student operations
import com.sms.service.StudentService;

// Used for validating the request body input
import jakarta.validation.Valid;

// Lombok annotation to auto-generate a constructor for final fields
import lombok.RequiredArgsConstructor;

// Marks this class as a REST controller, making it eligible for Spring's HTTP request handling
@RestController

// Maps all requests under "/students" to this controller
@RequestMapping("/students")

// Generates a constructor to inject the required dependencies (`studentService` in this case)
@RequiredArgsConstructor
public class StudentController {

    // Injected StudentService to delegate business logic
    private final StudentService studentService;

    // CREATE: Adds a new student using POST request
    @PostMapping("/add")
    public ResponseEntity<StudentResponseDTO> addStudent(@Valid @RequestBody StudentRequestDTO dto) {
        // Calls the service method and returns a 200 OK response with created student
        return ResponseEntity.ok(studentService.addStudent(dto));
    }

    // READ ALL: Retrieves all students using GET request
    @GetMapping("/all")
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        // Returns the list of all students in 200 OK response
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // READ BY ID: Retrieves a single student by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id) {
        // Calls service method and returns student with the given ID
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    // UPDATE: Updates an existing student using PUT request
    @PutMapping("/update/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDTO dto) {
        // Calls service method and returns the updated student
        return ResponseEntity.ok(studentService.updateStudent(id, dto));
    }

    // DELETE: Deletes a student by ID using DELETE request
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        // Calls service method to delete student
        studentService.deleteStudent(id);
        // Returns a confirmation message
        return ResponseEntity.ok("Student with ID " + id + " was deleted successfully.");
    }
}

