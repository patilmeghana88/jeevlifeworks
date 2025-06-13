package com.library.model;

public class TeacherMember extends Member {

	// Constructor to initialize the teacher's name, email, and phone
	public TeacherMember(String name, String email, String phone) {
		super(name, email, phone); // Call the parent (Member) constructor to set basic info
		this.maxBooksAllowed = 5; // Teachers are allowed to issue up to 5 books
	}

	// Overrides the abstract method from Member
	public int getMaxAllowedDays() {
		return 30; // Teachers can keep books for 30 days
	}

	// Overrides the abstract method from Member
	public String getMemberType() {
		return "Teacher"; // Identifies this member as a teacher
	}
}