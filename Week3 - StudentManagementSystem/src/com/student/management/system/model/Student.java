package com.student.management.system.model;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable, Comparable<Student> {

private static final long serialVersionUID = 1L;
 
 private int id;
 private String name;
 private int age;
 private String grade;
 private String address;

 public Student(int id, String name, int age, String grade, String address) {
     this.id = id;              
     this.name = name;      
     this.age = age;           
     this.grade = grade;        
     this.address = address;   
 }

 // Getter method for ID
 public int getId() { return id; }

 // Setter method for ID
 public void setId(int id) { this.id = id; }

 // Getter method for Name
 public String getName() { return name; }

 // Setter method for Name
 public void setName(String name) { this.name = name; }

 // Getter method for Age
 public int getAge() { return age; }

 // Setter method for Age
 public void setAge(int age) { this.age = age; }

 // Getter method for Grade
 public String getGrade() { return grade; }

 // Setter method for Grade
 public void setGrade(String grade) { this.grade = grade; }

 // Getter method for Address
 public String getAddress() { return address; }

 // Setter method for Address
 public void setAddress(String address) { this.address = address; }

 @Override
 public String toString() {
     return id + " | " + name + " | Age: " + age + " | Grade: " + grade + " | Address: " + address;
 }

 @Override
 public boolean equals(Object o) {
     if (this == o) return true;                  
     if (!(o instanceof Student)) return false; 
     Student student = (Student) o;                  
     return id == student.id;                       
 }

 @Override
 public int hashCode() {
     return Objects.hash(id);                   
 }

 @Override
 public int compareTo(Student other) {
     return Integer.compare(this.id, other.id);  
 }
}
