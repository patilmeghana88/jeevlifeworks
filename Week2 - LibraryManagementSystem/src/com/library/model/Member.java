package com.library.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Member {

	public UUID memberID = UUID.randomUUID(); // Unique ID generated for each member
	public String name, email, phone; // Member's personal details
	public int maxBooksAllowed; // Limit for how many books a member can borrow
	public List<Book> currentlyIssuedBooks = new ArrayList<>(); // List to keep track of books currently issued

	// Constructor to initialize common member details
	public Member(String name, String email, String phone) {
		this.name = name; // Assign the name
		this.email = email; // Assign the email
		this.phone = phone; // Assign the phone number
	}

	// Getter method to return the unique member ID
	public UUID getMemberID() {
		return memberID;
	}

	// Getter method to return the member's name
	public String getName() {
		return name;
	}

	// Getter method to return the member's email
	public String getEmail() {
		return email;
	}

	// Getter method to return the member's phone number
	public String getPhone() {
		return phone;
	}

	// Getter method to return the list of books currently issued to this member
	public List<Book> getCurrentlyIssuedBooks() {
		return currentlyIssuedBooks;
	}

	// Abstract method to be implemented by subclasses to define how many days a
	// book can be issued
	public abstract int getMaxAllowedDays();

	// Abstract method to be implemented by subclasses to return the type of member
	// (e.g., Student, Teacher, Guest)
	public abstract String getMemberType();
}