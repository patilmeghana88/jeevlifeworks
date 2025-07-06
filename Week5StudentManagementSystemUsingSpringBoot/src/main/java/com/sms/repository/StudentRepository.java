package com.sms.repository;

// Imports Spring Data JPA's JpaRepository interface, which provides CRUD operations and more
import org.springframework.data.jpa.repository.JpaRepository;

// Imports the Student entity class, which this repository will manage
import com.sms.model.Student;

// This interface is a Spring Data JPA repository for the Student entity with primary key type Long
public interface StudentRepository extends JpaRepository<Student, Long> {
    // No need to write implementation code — JpaRepository provides basic CRUD and query methods
}
