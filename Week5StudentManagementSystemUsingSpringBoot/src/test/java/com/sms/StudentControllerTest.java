package com.sms;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.sms.controller.StudentController;
import com.sms.dto.StudentRequestDTO;
import com.sms.dto.StudentResponseDTO;
import com.sms.service.StudentService;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.assertj.core.api.Assertions.assertThat;
public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private StudentRequestDTO requestDTO;
    private StudentResponseDTO responseDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        requestDTO = StudentRequestDTO.builder()
                .name("John Doe")
                .age(20)
                .grade("A")
                .address("123 Street")
                .build();

        responseDTO = StudentResponseDTO.builder()
                .id(1L)
                .name("John Doe")
                .age(20)
                .grade("A")
                .address("123 Street")
                .build();
    }

    @Test
    public void testAddStudent() {
        when(studentService.addStudent(requestDTO)).thenReturn(responseDTO);

        ResponseEntity<StudentResponseDTO> response = studentController.addStudent(requestDTO);

        assertThat(response.getBody()).isEqualTo(responseDTO);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testGetAllStudents() {
        when(studentService.getAllStudents()).thenReturn(Arrays.asList(responseDTO));

        ResponseEntity<List<StudentResponseDTO>> response = studentController.getAllStudents();

        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testGetStudentById() {
        when(studentService.getStudentById(1L)).thenReturn(responseDTO);

        ResponseEntity<StudentResponseDTO> response = studentController.getStudentById(1L);

        assertThat(response.getBody()).isEqualTo(responseDTO);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testUpdateStudent() {
        when(studentService.updateStudent(1L, requestDTO)).thenReturn(responseDTO);

        ResponseEntity<StudentResponseDTO> response = studentController.updateStudent(1L, requestDTO);

        assertThat(response.getBody()).isEqualTo(responseDTO);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testDeleteStudent() {
        doNothing().when(studentService).deleteStudent(1L);

        ResponseEntity<String> response = studentController.deleteStudent(1L);

        assertThat(response.getBody()).isEqualTo("Student with ID 1 was deleted successfully.");
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
}