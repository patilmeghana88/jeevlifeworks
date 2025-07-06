package com.sms.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.sms.entity.Student;
import com.sms.util.DbUtil.DbUtil;

public class StudentDaoImpl implements StudentDao {
	
	static {
	    try {
	        // Get current working directory
	        String baseDir = System.getProperty("user.dir");
	        String logDirPath = baseDir + File.separator + "logs";

	        // Create logs directory if it doesn't exist
	        File logDir = new File(logDirPath);
	        if (!logDir.exists()) {
	            logDir.mkdirs();
	        }

	        // Define full log file path
	        String logFilePath = logDirPath + File.separator + "student-management.log";

	        // Set up logger
	        LogManager.getLogManager().reset();
	        FileHandler fileHandler = new FileHandler(logFilePath, true);
	        fileHandler.setFormatter(new SimpleFormatter());

	        Logger rootLogger = Logger.getLogger("");
	        rootLogger.setUseParentHandlers(false);
	        rootLogger.addHandler(fileHandler);

	        System.out.println("Logging to: " + logFilePath); // optional debug

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


    private static final Logger logger = Logger.getLogger(StudentDaoImpl.class.getName());

    @Override
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (name, age, email, course) VALUES (?, ?, ?, ?)";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getCourse());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            logger.severe("Error adding student: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, age = ?, email = ?, course = ? WHERE id = ?";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getCourse());
            ps.setInt(5, student.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            logger.severe("Error updating student: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            logger.severe("Error deleting student: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Student getStudentById(int id) {
    	
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("email"),
                    rs.getString("course")
                );
            }
        } catch (Exception e) {
            logger.severe("Error fetching student by id: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                students.add(new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("email"),
                    rs.getString("course")
                ));
            }
        } catch (Exception e) {
            logger.severe("Error fetching all students: " + e.getMessage());
        }
        return students;
    }
}