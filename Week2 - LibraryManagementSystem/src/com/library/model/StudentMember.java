package com.library.model;

public class StudentMember extends Member {

	// Constructor to initialize student member details
	public StudentMember(String name, String email, String phone) {
		super(name, email, phone); // Call the constructor of the parent (Member) class
		this.maxBooksAllowed = 3; // Students can borrow up to 3 books
	}

	// This method overrides the abstract method in Member
	public int getMaxAllowedDays() {
		return 14; // Students can keep books for 14 days
	}

	// This method overrides the abstract method in Member
	public String getMemberType() {
		return "Student"; // Identifies this member as a student
	}
}