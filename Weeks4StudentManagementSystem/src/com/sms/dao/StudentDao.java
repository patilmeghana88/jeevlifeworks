package com.sms.dao;

import java.util.List;

import com.sms.entity.Student;

public interface StudentDao {
	boolean addStudent(Student student);
    boolean updateStudent(Student student);
    boolean deleteStudent(int id);
    Student getStudentById(int id);
    List<Student> getAllStudents();
}
