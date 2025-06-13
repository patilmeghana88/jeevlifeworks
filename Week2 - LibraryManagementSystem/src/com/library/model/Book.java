package com.library.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

public class Book {

	// Unique identifier for each book
	private final UUID bookID;

	// Book details
	private String title, author, genre;

	// Book status
	private boolean isIssued; // Indicates whether the book is currently issued
	private Member issuedTo; // Reference to the member who has issued the book
	private LocalDate dueDate; // Due date for the issued book

	// Queue of members who have reserved the book
	private Queue<Member> reservationQueue = new LinkedList<>();

	// Constructor to initialize a new Book with title, author, and genre
	public Book(String title, String author, String genre) {
		this.bookID = UUID.randomUUID(); // Generate a unique ID for the book
		this.title = title; // Set the title
		this.author = author; // Set the author
		this.genre = genre; // Set the genre
		this.isIssued = false; // Initially, the book is not issued
	}

	// Getter for book ID
	public UUID getBookID() {
		return bookID;
	}

	// Getter for title
	public String getTitle() {
		return title;
	}

	// Getter for author
	public String getAuthor() {
		return author;
	}

	// Getter for genre
	public String getGenre() {
		return genre;
	}

	// Getter to check if the book is issued
	public boolean isIssued() {
		return isIssued;
	}

	// Getter to know which member issued the book
	public Member getIssuedTo() {
		return issuedTo;
	}

	// Getter for due date
	public LocalDate getDueDate() {
		return dueDate;
	}

	// Getter for reservation queue
	public Queue<Member> getReservationQueue() {
		return reservationQueue;
	}

	// Setter to update issued status
	public void setIssued(boolean issued) {
		isIssued = issued;
	}

	// Setter to assign the member who issued the book
	public void setIssuedTo(Member member) {
		issuedTo = member;
	}

	// Setter to assign a due date to the book
	public void setDueDate(LocalDate date) {
		dueDate = date;
	}
}
