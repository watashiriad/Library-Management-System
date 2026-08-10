package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Borrowing implements Serializable {
    private static final long serialVersionUID = 1L;
    private int bookId;
    private String memberUsername;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public Borrowing(int bookId, String memberUsername) {
        this.bookId = bookId;
        this.memberUsername = memberUsername;
        this.borrowDate = LocalDate.now();
    }
    public int getBookId() {
        return bookId;
    }

    public String getMemberUsername() {
        return memberUsername;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void markReturned() {
        returnDate = LocalDate.now();
    }

    public boolean isReturned() {
        return returnDate != null;
    }
}