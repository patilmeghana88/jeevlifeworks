package com.library.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import com.library.model.Book;
import com.library.model.Member;

public class LibraryService {

	// Map to store books with their unique UUID as the key
	private Map<UUID, Book> bookCatalog = new HashMap<>();

	// Map to store members with their email as the key
	private Map<String, Member> members = new HashMap<>();

	// Method to register a new member
	public void registerMember(Member member) throws Exception {
		// Check if member with the same email already exists
		if (members.containsKey(member.getEmail()))
			throw new Exception("Member with this email already exists.");
		// Add member to the map
		members.put(member.getEmail(), member);
	}

	// Method to add a new book
	public void addBook(Book book) throws Exception {
		// Check if book with same UUID already exists
		if (bookCatalog.containsKey(book.getBookID()))
			throw new Exception("Duplicate book ID.");
		// Add book to the catalog
		bookCatalog.put(book.getBookID(), book);
	}

	// Method to remove a book from the catalog
	public void removeBook(Book book) throws Exception {
		// Only remove if not currently issued
		if (book.isIssued())
			throw new Exception("Book is currently issued.");
		bookCatalog.remove(book.getBookID());
	}

	// Method to issue a book to a member
	public void issueBook(Book book, Member member) throws Exception {
		// Book already issued
		if (book.isIssued())
			throw new Exception("Book already issued.");

		// Check if member has reached the book limit
		if (member.getCurrentlyIssuedBooks().size() >= member.maxBooksAllowed)
			throw new Exception("Book limit exceeded.");

		// Mark book as issued
		book.setIssued(true);
		book.setIssuedTo(member);

		// Set the due date according to member type
		book.setDueDate(LocalDate.now().plusDays(member.getMaxAllowedDays()));

		// Add book to member's issued book list
		member.getCurrentlyIssuedBooks().add(book);
	}

	// Method to return a book
	public void returnBook(Book book, Member member) {
		// Remove book from member's issued list
		member.getCurrentlyIssuedBooks().remove(book);

		// Reset book properties
		book.setIssued(false);
		book.setIssuedTo(null);
		book.setDueDate(null);

		// Check if there is a reservation queue
		if (!book.getReservationQueue().isEmpty()) {
			// Get the next member in queue
			Member nextMember = book.getReservationQueue().poll();
			try {
				// Try issuing the book to next member
				issueBook(book, nextMember);
				System.out.println("Auto-issued to: " + nextMember.getName());

				// Calculate remaining days
				long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), book.getDueDate());
				System.out.println(book.getTitle() + " due in " + daysLeft + " days");

			} catch (Exception e) {
				System.out.println("Reservation failed: " + e.getMessage());
			}
		}
	}

	// Method to search books using a keyword
	public List<Book> searchBooks(String keyword) {
		List<Book> result = new ArrayList<>(); // List to store matched books
		String lowerKeyword = keyword.toLowerCase(); // Convert keyword to lowercase

		for (Book b : bookCatalog.values()) {
			// Check if title, author, or genre matches keyword
			if (b.getTitle().toLowerCase().contains(lowerKeyword) || b.getAuthor().toLowerCase().contains(lowerKeyword)
					|| b.getGenre().toLowerCase().contains(lowerKeyword)) {
				result.add(b);
			}
		}

		return result;
	}

	// Method to reserve a book
	public void reserveBook(Book book, Member member) {
		// Only allow reservation if book is already issued
		if (!book.isIssued())
			return;

		// Add member to reservation queue
		book.getReservationQueue().add(member);
	}

	// Method to view all books issued to a member
	public void viewIssuedBooks(Member member) {

		for (Book book : member.getCurrentlyIssuedBooks()) {
			// Calculate remaining days till due date
			long daysLeft = LocalDate.now().until(book.getDueDate()).getDays();
			System.out.println(book.getTitle() + " due in " + daysLeft + " days");
		}
	}

	// Method to display overdue books
	public void viewOverdueBooks() {
		System.out.println("Overdue Books:");

		for (Book book : bookCatalog.values()) {
			// If book is issued and past its due date
			if (book.isIssued() && book.getDueDate().isBefore(LocalDate.now())) {
				System.out.println(book.getTitle() + " issued to " + book.getIssuedTo().getName() + " (Due date: "
						+ book.getDueDate() + ")");
			}
		}
	}
}
