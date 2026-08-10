package util;

import java.util.Scanner;

public final class InputValidator {

    private InputValidator() {
    }

    public static int getPositiveInt(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            if (scanner.hasNextInt()) {

                int value =
                        scanner.nextInt();

                scanner.nextLine();

                if (value > 0) {
                    return value;
                }
            } else {

                scanner.nextLine();
            }

            System.out.println(
                    "Please enter a positive number."
            );
        }
    }

    public static String getText(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String value =
                    scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println(
                    "Input cannot be empty."
            );
        }
    }

    public static boolean getYesNo(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String answer =
                    scanner.nextLine()
                            .trim()
                            .toLowerCase();

            if (answer.equals("y")
                    || answer.equals("yes")) {

                return true;
            }

            if (answer.equals("n")
                    || answer.equals("no")) {

                return false;
            }

            System.out.println(
                    "Please enter y or n."
            );
        }
    }
}