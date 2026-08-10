package util;

import model.Book;
import model.Borrowing;

import java.io.*;
import java.util.ArrayList;

public final class FileManager {

    private static final String DATA_FOLDER = "data";
    private static final String BOOK_FILE =
            DATA_FOLDER + File.separator + "books.ser";

    private static final String BORROWING_FILE =
            DATA_FOLDER + File.separator + "borrowings.ser";

    private FileManager() {
    }

    public static void saveBooks(
            ArrayList<Book> books) {

        createDataFolder();

        try (
                ObjectOutputStream output =
                        new ObjectOutputStream(
                                new FileOutputStream(BOOK_FILE)
                        )
        ) {

            output.writeObject(books);

        } catch (IOException e) {

            System.out.println(
                    "Error saving books: "
                            + e.getMessage()
            );
        }
    }


    @SuppressWarnings("unchecked")
    public static ArrayList<Book> loadBooks() {

        createDataFolder();

        File file = new File(BOOK_FILE);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (
                ObjectInputStream input =
                        new ObjectInputStream(
                                new FileInputStream(file)
                        )
        ) {

            return (ArrayList<Book>) input.readObject();

        } catch (
                IOException |
                ClassNotFoundException e
        ) {

            System.out.println(
                    "Could not load books."
            );

            return new ArrayList<>();

        } finally {

            System.out.println(
                    "Book data loading completed."
            );
        }
    }


    public static void saveBorrowings(
            ArrayList<Borrowing> borrowings) {

        createDataFolder();

        try (
                ObjectOutputStream output =
                        new ObjectOutputStream(
                                new FileOutputStream(
                                        BORROWING_FILE
                                )
                        )
        ) {

            output.writeObject(borrowings);

        } catch (IOException e) {

            System.out.println(
                    "Error saving borrowing records."
            );
        }
    }


    @SuppressWarnings("unchecked")
    public static ArrayList<Borrowing>
    loadBorrowings() {

        createDataFolder();

        File file = new File(BORROWING_FILE);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (
                ObjectInputStream input =
                        new ObjectInputStream(
                                new FileInputStream(file)
                        )
        ) {

            return (ArrayList<Borrowing>)
                    input.readObject();

        } catch (
                IOException |
                ClassNotFoundException e
        ) {

            return new ArrayList<>();

        } finally {

            System.out.println(
                    "Borrowing data loading completed."
            );
        }
    }

    private static void createDataFolder() {

        File folder = new File(DATA_FOLDER);

        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}