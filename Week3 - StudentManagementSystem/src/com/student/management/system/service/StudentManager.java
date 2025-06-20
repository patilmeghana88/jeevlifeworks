package com.student.management.system.service;

import java.io.*;
import java.util.*;

import com.student.management.system.model.Student;

public class StudentManager {

 // List to maintain student records in order of insertion
 private ArrayList<Student> students = new ArrayList<>();

 private HashMap<Integer, Student> studentMap = new HashMap<>();

 private TreeSet<Student> studentSet = new TreeSet<>();

 // Adds a new student to all collections after checking for unique ID
 public void addStudent(Student student) {
     if (studentMap.containsKey(student.getId())) {
         // If the ID already exists, show an error and return
         System.out.println("Error: Student ID already exists!");
         return;
     }
     // Add student to all collections
     students.add(student);
     studentMap.put(student.getId(), student);
     studentSet.add(student);
     System.out.println("Student added successfully.");
 }

 // Removes a student by ID
 public void removeStudent(int id) {
     // Remove from HashMap and store the object (or null if not found)
     Student student = studentMap.remove(id);
     if (student != null) {
         // If student was found, remove from other collections too
         students.remove(student);
         studentSet.remove(student);
         System.out.println("Student removed.");
     } else {
         // If not found in HashMap, show message
         System.out.println("Student not found.");
     }
 }

 // Updates a student's details using their ID
 public void updateStudent(int id, String name, int age, String grade, String address) {
     // Get student from HashMap
     Student student = studentMap.get(id);
     if (student != null) {
         // Remove from TreeSet before updating (since sorting might be affected)
         studentSet.remove(student);

         // Update the fields of the student
         student.setName(name);
         student.setAge(age);
         student.setGrade(grade);
         student.setAddress(address);

         // Add back to TreeSet
         studentSet.add(student);
         System.out.println("Student updated.");
     } else {
         // If student not found
         System.out.println("Student not found.");
     }
 }

 // Searches for a student by ID and returns the object
 public Student searchStudent(int id) {
     return studentMap.get(id);
 }

 // Displays all students in sorted order (based on compareTo logic in Student)
 public void displayAllStudents() {
     if (studentSet.isEmpty()) {
         System.out.println("No student records.");
         return;
     }

     // Iterate and print each student
     for (Student s : studentSet) {
         System.out.println(s);
     }
 }

 // Saves all student data to a file (serialization)
 public void saveToFile(String filename) {
     try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
         // Serialize the ArrayList of students to file
         oos.writeObject(students);
         System.out.println("Data saved.");
     } catch (IOException e) {
         // Handle exceptions during save
         System.out.println("Error saving data: " + e.getMessage());
     }
 }

 // Loads student data from file (deserialization)
 public void loadFromFile(String filename) {
     try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
      
         students = (ArrayList<Student>) ois.readObject();

         studentMap.clear();
         studentSet.clear();

         for (Student s : students) {
             studentMap.put(s.getId(), s);
             studentSet.add(s);
         }

         System.out.println("Data loaded.");
     } catch (IOException | ClassNotFoundException e) {
         // If file not found or reading error occurs
         System.out.println("No existing data found.");
     }
 }
}