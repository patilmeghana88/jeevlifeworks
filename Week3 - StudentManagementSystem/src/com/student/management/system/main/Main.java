package com.student.management.system.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.student.management.system.model.Student;
import com.student.management.system.service.StudentManager;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        manager.loadFromFile("students.dat");

        int choice = 0;

        // Menu loop continues until user selects option 6 to exit
        do {
            try {
                System.out.println("\n---- Student Management System ----");
                System.out.println("1. Add new student");
                System.out.println("2. Remove student");
                System.out.println("3. Update student");
                System.out.println("4. Search student");
                System.out.println("5. Display all students");
                System.out.println("6. Exit and save");
                System.out.print("Enter choice: ");

                choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                switch (choice) {
                    case 1:
                        System.out.print("ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Grade: ");
                        String grade = scanner.nextLine();

                        System.out.print("Address: ");
                        String address = scanner.nextLine();

                        if (age > 0 && name != null && !name.isBlank() && address != null && !address.isBlank()) {
                            manager.addStudent(new Student(id, name, age, grade, address));
                        } else {
                            System.out.println("Invalid input!");
                        }
                        break;

                    case 2:
                        System.out.print("Enter ID to remove: ");
                        int removeId = scanner.nextInt();
                        manager.removeStudent(removeId);
                        break;

                    case 3:
                        System.out.print("Enter ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("New Name: ");
                        String newName = scanner.nextLine();

                        System.out.print("New Age: ");
                        int newAge = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("New Grade: ");
                        String newGrade = scanner.nextLine();

                        System.out.print("New Address: ");
                        String newAddress = scanner.nextLine();

                        manager.updateStudent(updateId, newName, newAge, newGrade, newAddress);
                        break;

                    case 4:
                        System.out.print("Enter ID to search: ");
                        int searchId = scanner.nextInt();
                        Student found = manager.searchStudent(searchId);
                        System.out.println(found != null ? found : "Student not found.");
                        break;

                    case 5:
                        manager.displayAllStudents();
                        break;

                    case 6:
                        manager.saveToFile("students.dat");
                        System.out.println("Exiting. Bye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Invalid input type. Please enter the correct data (e.g., numbers only where expected).");
                scanner.nextLine(); // Clear the invalid input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                scanner.nextLine(); // Clear buffer
            }
        } while (choice != 6);

        scanner.close();
    }
}
