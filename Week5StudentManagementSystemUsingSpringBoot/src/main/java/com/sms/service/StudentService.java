package com.sms.service;

// Importing the List interface to return multiple student response objects
import java.util.List;

// Importing the DTO class used to accept input data for student creation or update
import com.sms.dto.StudentRequestDTO;

// Importing the DTO class used to return structured student data as a response
import com.sms.dto.StudentResponseDTO;

// Service interface that defines the contract for student-related operations
public interface StudentService {

    // Adds a new student based on the provided request data and returns the created student's details
    StudentResponseDTO addStudent(StudentRequestDTO dto);

    // Returns a list of all students as response DTOs
    List<StudentResponseDTO> getAllStudents();

    // Retrieves a student by their ID and returns their details
    StudentResponseDTO getStudentById(Long id);

    // Updates an existing student by ID with new data and returns the updated student's details
    StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto);

    // Deletes a student by their ID
    void deleteStudent(Long id);
}
