package com.sms.service;

import java.util.List;

import com.sms.entity.Student;

public interface StudentService {
    boolean registerStudent(Student student);
    boolean modifyStudent(Student student);
    boolean removeStudent(int id);
    Student fetchStudentById(int id);
    List<Student> fetchAllStudents();
}
