package model;

import exception.BookAlreadyBorrowedException;
import exception.InvalidInputException;

import java.io.Serializable;

public class Book implements Serializable, Borrowable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private Author author;
    private String category;
    private boolean available;

    public Book() {
        available = true;
    }

    public Book(
            int id,
            String title,
            Author author,
            String category) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Book ID must be positive."
            );
        }

        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.available = true;
    }


    public Book(Book other) {
        this.id = other.id;
        this.title = other.title;
        this.author = new Author(other.author);
        this.category = other.category;
        this.available = other.available;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


    @Override
    public void borrow() {

        if (!available) {
            throw new BookAlreadyBorrowedException(
                    "This book is already borrowed."
            );
        }

        available = false;
    }

    @Override
    public void returnBook() {

        if (available) {
            throw new InvalidInputException(
                    "This book is already available."
            );
        }

        available = true;
    }

    public void displayBook() {

        String status =
                available ? "Available" : "Borrowed";

        System.out.printf(
                "%-5d %-28s %-22s %-15s %-12s%n",
                id,
                title,
                author.getName(),
                category,
                status
        );
    }

    @Override
    public String toString() {

        return id + " - "
                + title + " - "
                + author.getName() + " - "
                + category;
    }
}