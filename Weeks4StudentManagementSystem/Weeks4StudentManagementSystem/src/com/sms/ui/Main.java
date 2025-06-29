package com.sms.ui;

import com.sms.entity.Student;
import com.sms.service.StudentService;
import com.sms.service.StudentServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentServiceImpl();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. List All Students");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter course: ");
                    String course = scanner.nextLine();
                    Student student = new Student(0, name, age, email, course);
                    System.out.println(service.registerStudent(student) ? "Added successfully" : "Failed to add");
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    Student s = service.fetchStudentById(scanner.nextInt());
                    System.out.println(s != null ? s : "Student not found");
                    break;
                case 3:
                    System.out.print("Enter student ID to update: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Student existing = service.fetchStudentById(id);
                    if (existing == null) {
                        System.out.println("Student not found");
                        break;
                    }
                    System.out.print("Enter new name: ");
                    existing.setName(scanner.nextLine());
                    System.out.print("Enter new age: ");
                    existing.setAge(scanner.nextInt());
                    scanner.nextLine();
                    System.out.print("Enter new email: ");
                    existing.setEmail(scanner.nextLine());
                    System.out.print("Enter new course: ");
                    existing.setCourse(scanner.nextLine());
                    System.out.println(service.modifyStudent(existing) ? "Updated successfully" : "Failed to update");
                    break;
                case 4:
                    System.out.print("Enter student ID to delete: ");
                    System.out.println(service.removeStudent(scanner.nextInt()) ? "Deleted successfully" : "Failed to delete");
                    break;
                case 5:
                    List<Student> students = service.fetchAllStudents();
                    if (students.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        students.forEach(System.out::println);
                    }
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }
}
