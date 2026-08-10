package service;

import exception.BookAlreadyBorrowedException;
import exception.BookNotFoundException;
import model.Book;
import model.Borrowing;
import util.FileManager;

import java.util.ArrayList;
import java.util.Comparator;

public class LibraryService {

    private ArrayList<Book> books;
    private ArrayList<Borrowing> borrowings;

    public LibraryService() {

        books = FileManager.loadBooks();
        borrowings = FileManager.loadBorrowings();
    }

    public void addBook(Book book) {

        for (Book existing : books) {

            if (existing.getId() == book.getId()) {

                throw new IllegalArgumentException(
                        "A book with this ID already exists."
                );
            }
        }

        books.add(book);

        saveData();

        System.out.println(
                "Book added successfully."
        );
    }

    public ArrayList<Book> getBooks() {
        return new ArrayList<>(books);
    }


    public void viewBooks() {

        if (books.isEmpty()) {

            System.out.println(
                    "No books found."
            );

            return;
        }

        printHeader();

        for (Book book : books) {
            book.displayBook();
        }
    }

    public Book searchBook(int id)
            throws BookNotFoundException {

        for (Book book : books) {

            if (book.getId() == id) {
                return book;
            }
        }

        throw new BookNotFoundException(
                "Book with ID " + id
                        + " was not found."
        );
    }


    public ArrayList<Book> searchBook(
            String title) {

        ArrayList<Book> results =
                new ArrayList<>();

        for (Book book : books) {

            if (book.getTitle()
                    .toLowerCase()
                    .contains(
                            title.toLowerCase()
                    )) {

                results.add(book);
            }
        }

        return results;
    }


    public ArrayList<Book> filterByCategory(
            String category) {

        ArrayList<Book> results =
                new ArrayList<>();

        for (Book book : books) {

            if (book.getCategory()
                    .equalsIgnoreCase(category)) {

                results.add(book);
            }
        }

        return results;
    }


    public void sortByTitle() {

        books.sort(
                Comparator.comparing(
                        Book::getTitle,
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        saveData();
    }


    public void updateBook(
            int id,
            String title,
            model.Author author,
            String category)
            throws BookNotFoundException {

        Book book = searchBook(id);

        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(category);

        saveData();

        System.out.println(
                "Book updated successfully."
        );
    }


    public void deleteBook(int id)
            throws BookNotFoundException {

        Book book = searchBook(id);

        if (!book.isAvailable()) {

            throw new IllegalStateException(
                    "Borrowed books cannot be deleted."
            );
        }

        books.remove(book);

        saveData();

        System.out.println(
                "Book deleted successfully."
        );
    }


    public void borrowBook(
            int bookId,
            String username)
            throws BookNotFoundException {

        Book book = searchBook(bookId);

        book.borrow();

        Borrowing borrowing =
                new Borrowing(
                        bookId,
                        username
                );

        borrowings.add(borrowing);

        saveData();

        System.out.println(
                "Book borrowed successfully."
        );
    }


    public void returnBook(
            int bookId,
            String username)
            throws BookNotFoundException {

        Book book = searchBook(bookId);

        Borrowing activeBorrowing =
                null;

        for (Borrowing borrowing : borrowings) {

            if (borrowing.getBookId() == bookId
                    && borrowing
                    .getMemberUsername()
                    .equals(username)
                    && !borrowing.isReturned()) {

                activeBorrowing = borrowing;
                break;
            }
        }

        if (activeBorrowing == null) {

            throw new IllegalStateException(
                    "You do not have this book borrowed."
            );
        }

        book.returnBook();

        activeBorrowing.markReturned();

        saveData();

        System.out.println(
                "Book returned successfully."
        );
    }


    public int getTotalBooks() {
        return books.size();
    }


    public int getAvailableBooks() {

        int count = 0;

        for (Book book : books) {

            if (book.isAvailable()) {
                count++;
            }
        }

        return count;
    }


    public int getBorrowedBooks() {

        return getTotalBooks()
                - getAvailableBooks();
    }


    public ArrayList<Borrowing>
    getBorrowings() {

        return new ArrayList<>(borrowings);
    }

    private void saveData() {

        FileManager.saveBooks(books);
        FileManager.saveBorrowings(borrowings);
    }

    private void printHeader() {

        System.out.println();

        System.out.printf(
                "%-5s %-28s %-22s %-15s %-12s%n",
                "ID",
                "Title",
                "Author",
                "Category",
                "Status"
        );

        System.out.println(
                "--------------------------------------------------------------------------------"
        );
    }
}