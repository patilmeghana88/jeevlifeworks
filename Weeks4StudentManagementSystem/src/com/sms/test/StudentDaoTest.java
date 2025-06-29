package com.sms.test;

import com.sms.dao.StudentDao;
import com.sms.dao.StudentDaoImpl;
import com.sms.entity.Student;
import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class StudentDaoTest {

    private static StudentDao studentDao;

    @BeforeAll
    public static void setup() {
        studentDao = new StudentDaoImpl();
    }

    @Test
    public void testAddStudent() {
        Student student = new Student(0, "Test User", 20, "testuser@example.com", "CSE");
        assertTrue(studentDao.addStudent(student), "Student should be added successfully");
    }

    @Test
    public void testGetAllStudents() {
        List<Student> students = studentDao.getAllStudents();
        assertNotNull(students);
    }

    @Test
    public void testUpdateStudent() {
        List<Student> students = studentDao.getAllStudents();
        if (!students.isEmpty()) {
            Student student = students.get(0);
            student.setName("Updated Name");
            assertTrue(studentDao.updateStudent(student));
        } else {
            fail("No students to update");
        }
    }

    @Test
    public void testDeleteStudent() {
        List<Student> students = studentDao.getAllStudents();
        if (!students.isEmpty()) {
            int idToDelete = students.get(students.size() - 1).getId();
            assertTrue(studentDao.deleteStudent(idToDelete));
        } else {
            fail("No students to delete");
        }
    }

    @Test
    public void testGetStudentById() {
        List<Student> students = studentDao.getAllStudents();
        if (!students.isEmpty()) {
            int id = students.get(0).getId();
            assertNotNull(studentDao.getStudentById(id));
        } else {
            fail("No students to fetch");
        }
    }
}

