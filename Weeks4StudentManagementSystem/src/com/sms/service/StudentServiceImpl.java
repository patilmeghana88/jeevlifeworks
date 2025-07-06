package com.sms.service;

import com.sms.dao.StudentDao;
import com.sms.dao.StudentDaoImpl;
import com.sms.entity.Student;


import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao = new StudentDaoImpl();

    @Override
    public boolean registerStudent(Student student) {
        return studentDao.addStudent(student);
    }

    @Override
    public boolean modifyStudent(Student student) {
        return studentDao.updateStudent(student);
    }

    @Override
    public boolean removeStudent(int id) {
        return studentDao.deleteStudent(id);
    }

    @Override
    public Student fetchStudentById(int id) {
        return studentDao.getStudentById(id);
    }

    @Override
    public List<Student> fetchAllStudents() {
        return studentDao.getAllStudents();
    }
}