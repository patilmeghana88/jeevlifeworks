package com.sms.service;

// Imports List interface for handling collections of students
import java.util.List;

// Imports Collectors for converting streams to lists
import java.util.stream.Collectors;

// Imports SLF4J Logger for logging at various levels (info, debug, warn)
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Enables Spring’s dependency injection mechanism
import org.springframework.beans.factory.annotation.Autowired;

// Marks this class as a service component in Spring's component scan
import org.springframework.stereotype.Service;

// Imports request DTO used to create/update student data
import com.sms.dto.StudentRequestDTO;

// Imports response DTO used to return student data to the client
import com.sms.dto.StudentResponseDTO;

// Imports custom exception thrown when a student is not found
import com.sms.exception.StudentNotFoundException;

// Imports the Student entity class
import com.sms.model.Student;

// Imports the repository interface for database access
import com.sms.repository.StudentRepository;

// Lombok annotation that generates a constructor for final fields or those annotated with @NonNull
import lombok.RequiredArgsConstructor;

// Marks this class as a Spring-managed service
@Service

// Automatically generates a constructor for all required (final) dependencies
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    // Logger instance for this class to log runtime messages
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    // Injects the StudentRepository dependency to perform database operations
    @Autowired
    private StudentRepository repository;

    // Adds a new student and returns the saved student's details
    @Override
    public StudentResponseDTO addStudent(StudentRequestDTO dto) {
        logger.info("Adding new student: {}", dto.getName());

        // Creates a Student object using the builder pattern with data from the DTO
        Student student = Student.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .grade(dto.getGrade())
                .address(dto.getAddress())
                .build();

        // Saves the student entity to the database
        Student saved = repository.save(student);
        logger.debug("Student saved with ID: {}", saved.getId());

        // Converts and returns the saved Student as a response DTO
        return toResponseDTO(saved);
    }

    // Retrieves and returns a list of all students
    @Override
    public List<StudentResponseDTO> getAllStudents() {
        logger.info("Fetching all students");

        // Fetches all students and maps each entity to a response DTO
        List<StudentResponseDTO> students = repository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());

        logger.debug("Total students fetched: {}", students.size());
        return students;
    }

    // Fetches a single student by ID, or throws an exception if not found
    @Override
    public StudentResponseDTO getStudentById(Long id) {
        logger.info("Fetching student by ID: {}", id);

        // Looks up student or throws StudentNotFoundException if absent
        Student student = repository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Student not found with ID: {}", id);
                    return new StudentNotFoundException(id);
                });

        // Converts and returns the found student as a response DTO
        return toResponseDTO(student);
    }

    // Updates an existing student’s details by ID
    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto) {
        logger.info("Updating student with ID: {}", id);

        // Fetches the student to update, or throws exception if not found
        Student student = repository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Cannot update, student not found with ID: {}", id);
                    return new StudentNotFoundException(id);
                });

        // Updates the student entity with new values from the DTO
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setGrade(dto.getGrade());
        student.setAddress(dto.getAddress());

        // Saves the updated student back to the database
        Student updated = repository.save(student);
        logger.debug("Student updated: {}", updated.getId());

        // Converts and returns the updated student as a response DTO
        return toResponseDTO(updated);
    }

    // Deletes a student by ID
    @Override
    public void deleteStudent(Long id) {
        logger.info("Deleting student with ID: {}", id);

        // Checks if the student exists before attempting to delete
        if (!repository.existsById(id)) {
            logger.warn("Cannot delete, student not found with ID: {}", id);
            throw new StudentNotFoundException(id);
        }

        // Deletes the student
        repository.deleteById(id);
        logger.debug("Student deleted successfully with ID: {}", id);
    }

    // Converts a Student entity to a StudentResponseDTO
    private StudentResponseDTO toResponseDTO(Student student) {
        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setAge(student.getAge());
        dto.setGrade(student.getGrade());
        dto.setAddress(student.getAddress());
        return dto;
    }
}
