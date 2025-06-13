package com.library.main;

import java.time.LocalDate;

import com.library.model.Book;
import com.library.model.GuestMember;
import com.library.model.Member;
import com.library.model.StudentMember;
import com.library.model.TeacherMember;
import com.library.service.LibraryService;

public class Main {
	public static void main(String[] args) throws Exception {
		// Create an instance of LibraryService to manage the library operations
		LibraryService library = new LibraryService();

		// Create different types of members using polymorphism
		Member student = new StudentMember("Megha", "megha@gmail.com", "9998887771");
		Member teacher = new TeacherMember("Ravi", "ravi@gmail.com", "9998887772");
		Member guest = new GuestMember("Nina", "nina@gmail.com", "9998887773");

		// Create two books to add to the library
		Book book1 = new Book("Java Basics", "Herbert Schildt", "Programming");
		Book book2 = new Book("Effective Java", "Joshua Bloch", "Programming");

		// Register the members to the library
		library.registerMember(student);
		library.registerMember(teacher);
		library.registerMember(guest);

		// Add books to the library catalog
		library.addBook(book1);
		library.addBook(book2);

		// Issue book1 to the student
		library.issueBook(book1, student);
		System.out.println("Issued: " + book1.getTitle() + " to " + student.getName());

		// View the books issued to the student
		library.viewIssuedBooks(student);

		// Teacher reserves the book which is currently issued to the student
		library.reserveBook(book1, teacher);

		// Student returns the book; it should be auto-issued to the teacher
		library.returnBook(book1, student); // Ravi (teacher) will receive it next

		// Search for books that contain the keyword "Java"
		System.out.println("Searching for 'Java':");
		for (Book b : library.searchBooks("Java")) {
			System.out.println(b.getTitle() + " - Issued: " + b.isIssued());
		}

		book1.setDueDate(LocalDate.now().minusDays(1)); // This makes the book overdue

		// Display all overdue books and who they are issued to
		library.viewOverdueBooks();
	}
}
