package com.sms.exception;


//Declares a custom exception class named StudentNotFoundException that extends RuntimeException
public class StudentNotFoundException extends RuntimeException {

 // Constructor that takes a student ID as a parameter
 public StudentNotFoundException(Long id) {
     // Calls the constructor of the parent class (RuntimeException) with a custom error message
     super("Student with ID " + id + " not found.");
 }
}
