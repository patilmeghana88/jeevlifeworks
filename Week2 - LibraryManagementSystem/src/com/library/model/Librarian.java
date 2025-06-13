package com.library.model;

public class Librarian extends Member {

	// Constructor to initialize librarian's details
	public Librarian(String name, String email, String phone) {
		super(name, email, phone); // Call the constructor of the parent Member class
		this.maxBooksAllowed = Integer.MAX_VALUE; // Librarians have no book issuing limit
	}

	// Override of the abstract method from Member
	public int getMaxAllowedDays() {
		return 0; // Librarians typically don’t issue books for personal use, so this can be 0
	}

	// Override of the abstract method from Member
	public String getMemberType() {
		return "Librarian"; // Identifies this member as a librarian
	}
}