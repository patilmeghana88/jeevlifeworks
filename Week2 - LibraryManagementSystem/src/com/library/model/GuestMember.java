package com.library.model;

public class GuestMember extends Member {

	// Constructor to create a GuestMember object with name, email, and phone
	public GuestMember(String name, String email, String phone) {
		super(name, email, phone); // Call the constructor of the parent class (Member)
		this.maxBooksAllowed = 1; // Guests can issue only 1 book at a time
	}

	// Overridden method to return the maximum allowed days for issuing a book
	public int getMaxAllowedDays() {
		return 7; // Guests can keep a book for 7 days
	}

	// Overridden method to return the type of member
	public String getMemberType() {
		return "Guest"; // Identifies this member as a Guest
	}
}