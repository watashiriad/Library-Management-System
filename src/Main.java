import exception.BookNotFoundException;
import exception.InvalidInputException;
import model.Admin;
import model.Author;
import model.Book;
import model.Member;
import model.User;
import service.AuthService;
import service.LibraryService;
import service.ReportService;
import util.InputValidator;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner =
            new Scanner(System.in);

    private static final LibraryService library =
            new LibraryService();

    private static final AuthService auth =
            new AuthService();

    private static final ReportService report =
            new ReportService(library);

    public static void main(String[] args) {

        showWelcome();

        boolean running = true;

        while (running) {

            User user = login();

            if (user == null) {

                System.out.println(
                        "Invalid username or password."
                );
                boolean retry =
                        InputValidator.getYesNo(
                                scanner,
                                "Try again? (y/n): "
                        );

                if (!retry) {
                    running = false;
                }

            } else {

                user.showRole();

                if (user instanceof Admin) {

                    adminMenu(user);

                } else if (user instanceof Member) {

                    memberMenu(user);
                }
            }
        }

        scanner.close();

        System.out.println(
                "Thank you for using the Library Management System."
        );
    }

    private static void showWelcome() {

        System.out.println();
        System.out.println(
                "========================================"
        );
        System.out.println(
                "        LIBRARY MANAGEMENT SYSTEM"
        );
        System.out.println(
                "========================================="
        );

        System.out.println(
                "Default Admin  : admin / 1234"
        );

        System.out.println(
                "Default Member : member / 1234"
        );

        System.out.println(
                "========================================"
        );
    }

    private static User login() {

        System.out.println();
        System.out.println(
                "=============== LOGIN ==============="
        );

        String username =
                InputValidator.getText(
                        scanner,
                        "Username: "
                );

        String password =
                InputValidator.getText(
                        scanner,
                        "Password: "
                );

        return auth.login(
                username,
                password
        );
    }

    private static void adminMenu(User user) {

        while (true) {

            System.out.println();
            System.out.println(
                    "=============== ADMIN MENU ==============="
            );

            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Filter by Category");
            System.out.println("5. Sort by Title");
            System.out.println("6. Update Book");
            System.out.println("7. Delete Book");
            System.out.println("8. Borrow Book");
            System.out.println("9. Return Book");
            System.out.println("10. Library Report");
            System.out.println("11. Logout");

            int choice =
                    InputValidator.getPositiveInt(
                            scanner,
                            "Enter choice: "
                    );

            try {

                switch (choice) {

                    case 1:
                        addBook();
                        break;

                    case 2:
                        library.viewBooks();
                        break;

                    case 3:
                        searchBook();
                        break;

                    case 4:
                        filterBooks();
                        break;

                    case 5:
                        library.sortByTitle();

                        System.out.println(
                                "Books sorted by title."
                        );

                        library.viewBooks();
                        break;

                    case 6:
                        updateBook();
                        break;

                    case 7:
                        deleteBook();
                        break;

                    case 8:
                        borrowBook(user);
                        break;

                    case 9:
                        returnBook(user);
                        break;

                    case 10:
                        report.showSummary();
                        report.showCategoryReport();
                        break;

                    case 11:

                        System.out.println(
                                "Logging out..."
                        );

                        return;

                    default:

                        System.out.println(
                                "Invalid menu choice."
                        );
                }

            } catch (Exception e) {

                System.out.println(
                        "Error: "
                                + e.getMessage()
                );
            }
        }
    }

    private static void memberMenu(User user) {

        while (true) {

            System.out.println();
            System.out.println(
                    "=============== MEMBER MENU ==========="
            );

            System.out.println("1. View All Books");
            System.out.println("2. Search Book");
            System.out.println("3. Filter by Category");
            System.out.println("4. Sort by Title");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Library Report");
            System.out.println("8. Logout");

            int choice =
                    InputValidator.getPositiveInt(
                            scanner,
                            "Enter choice: "
                    );

            try {

                switch (choice) {

                    case 1:
                        library.viewBooks();
                        break;

                    case 2:
                        searchBook();
                        break;

                    case 3:
                        filterBooks();
                        break;

                    case 4:
                        library.sortByTitle();
                        library.viewBooks();
                        break;

                    case 5:
                        borrowBook(user);
                        break;

                    case 6:
                        returnBook(user);
                        break;

                    case 7:
                        report.showSummary();
                        break;

                    case 8:

                        System.out.println(
                                "Logging out..."
                        );

                        return;

                    default:

                        System.out.println(
                                "Invalid menu choice."
                        );
                }

            } catch (Exception e) {

                System.out.println(
                        "Error: "
                                + e.getMessage()
                );
            }
        }
    }

    private static void addBook() {

        System.out.println();
        System.out.println(
                "=============== ADD BOOK ==========="
        );

        int id =
                InputValidator.getPositiveInt(
                        scanner,
                        "Book ID: "
                );

        String title =
                InputValidator.getText(
                        scanner,
                        "Book title: "
                );

        String authorName =
                InputValidator.getText(
                        scanner,
                        "Author name: "
                );

        String category =
                InputValidator.getText(
                        scanner,
                        "Category: "
                );

        Author author =
                new Author(
                        id,
                        authorName
                );

        Book book =
                new Book(
                        id,
                        title,
                        author,
                        category
                );

        library.addBook(book);
    }

    private static void searchBook()
            throws BookNotFoundException {

        System.out.println();
        System.out.println(
                "=============== SEARCH ==========="
        );

        System.out.println("1. Search by ID");
        System.out.println("2. Search by title");
        int choice =
                InputValidator.getPositiveInt(
                        scanner,
                        "Choice: "
                );

        if (choice == 1) {

            int id =
                    InputValidator.getPositiveInt(
                            scanner,
                            "Book ID: "
                    );

            Book book =
                    library.searchBook(id);

            printBookHeader();

            book.displayBook();

        } else if (choice == 2) {

            String title =
                    InputValidator.getText(
                            scanner,
                            "Title: "
                    );

            ArrayList<Book> results =
                    library.searchBook(title);

            if (results.isEmpty()) {

                System.out.println(
                        "No matching books found."
                );

            } else {

                printBookHeader();

                for (Book book : results) {
                    book.displayBook();
                }
            }

        } else {

            throw new InvalidInputException(
                    "Invalid search option."
            );
        }
    }

    private static void filterBooks() {

        String category =
                InputValidator.getText(
                        scanner,
                        "Category: "
                );

        ArrayList<Book> results =
                library.filterByCategory(
                        category
                );

        if (results.isEmpty()) {

            System.out.println(
                    "No books found in this category."
            );

            return;
        }

        printBookHeader();

        for (Book book : results) {
            book.displayBook();
        }
    }

    private static void updateBook()
            throws BookNotFoundException {

        System.out.println();
        System.out.println(
                "=============== UPDATE BOOK ============"
        );

        int id =
                InputValidator.getPositiveInt(
                        scanner,
                        "Book ID: "
                );

        String title =
                InputValidator.getText(
                        scanner,
                        "New title: "
                );

        String authorName =
                InputValidator.getText(
                        scanner,
                        "New author: "
                );

        String category =
                InputValidator.getText(
                        scanner,
                        "New category: "
                );

        Author author =
                new Author(
                        id,
                        authorName
                );

        library.updateBook(
                id,
                title,
                author,
                category
        );
    }

    private static void deleteBook()
            throws BookNotFoundException {

        int id =
                InputValidator.getPositiveInt(
                        scanner,
                        "Book ID to delete: "
                );

        Book book =
                library.searchBook(id);

        printBookHeader();

        book.displayBook();

        boolean confirm =
                InputValidator.getYesNo(
                        scanner,
                        "Delete this book? (y/n): "
                );

        if (confirm) {

            library.deleteBook(id);

        } else {

            System.out.println(
                    "Delete cancelled."
            );
        }
    }

    private static void borrowBook(
            User user)
            throws BookNotFoundException {

        int id =
                InputValidator.getPositiveInt(
                        scanner,
                        "Book ID to borrow: "
                );

        library.borrowBook(
                id,
                user.getUsername()
        );
    }

    private static void returnBook(
            User user)
            throws BookNotFoundException {

        int id =
                InputValidator.getPositiveInt(
                        scanner,
                        "Book ID to return: "
                );

        library.returnBook(
                id,
                user.getUsername()
        );
    }

    private static void printBookHeader() {

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