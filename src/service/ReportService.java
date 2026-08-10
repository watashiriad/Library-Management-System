package service;

import model.Book;

public class ReportService {

    private LibraryService libraryService;


    public ReportService(
            LibraryService libraryService) {

        this.libraryService = libraryService;
    }

    public void showSummary() {

        System.out.println();
        System.out.println(
                "================================"
        );
        System.out.println(
                "          LIBRARY SUMMARY"
        );
        System.out.println(
                "==================================="
        );

        System.out.println(
                "Total Books     : "
                        + libraryService.getTotalBooks()
        );

        System.out.println(
                "Available Books : "
                        + libraryService.getAvailableBooks()
        );

        System.out.println(
                "Borrowed Books  : "
                        + libraryService.getBorrowedBooks()
        );

        System.out.println(
                "Total Borrowings: "
                        + libraryService
                        .getBorrowings()
                        .size()
        );

        System.out.println(
                "================================="
        );
    }

    /**
     * Displays books grouped by category.
     */
    public void showCategoryReport() {

        System.out.println();
        System.out.println(
                "========== CATEGORY REPORT ======"
        );

        for (Book book :
                libraryService.getBooks()) {

            System.out.println(
                    book.getCategory()
                            + " -> "
                            + book.getTitle()
            );
        }
    }
}