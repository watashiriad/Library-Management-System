# Library Management System

## Project Description

The **Library Management System** is a Java-based application developed using **Object-Oriented Programming (OOP)** concepts.

The system allows a library to efficiently manage **books, members, authors, users, and borrowing records**. It also includes user authentication, role-based access, file handling, input validation, and error handling.

---

## Features

* User Login
* Admin and Member Roles
* Add Books
* View Books
* Search Books
* Update Books
* Delete Books
* Add Members
* Borrow Books
* Return Books
* View Borrowing Records
* Generate Library Reports
* Save and Load Data Using File Handling
* Input Validation
* Error Handling
* Custom Exception Handling

---

## OOP Concepts Used

This project demonstrates the following **Object-Oriented Programming concepts**:

* **Encapsulation** — Protecting data using private fields and controlled access through methods.
* **Inheritance** — Creating child classes from existing parent classes.
* **Polymorphism** — Allowing the same method or reference to behave differently depending on the object.
* **Abstraction** — Hiding unnecessary implementation details and exposing only essential functionality.
* **Interface** — Defining common behaviors that classes can implement.
* **Method Overloading** — Using multiple methods with the same name but different parameters.
* **Method Overriding** — Redefining a parent class method in a child class.

---

## Technologies Used

* **Java**
* **Java OOP**
* **Java File I/O**
* **IntelliJ IDEA**
* **JDK 17 or Later**

---

## Project Structure

```text
src
│
├── Main.java
│
├── model
│   ├── User.java
│   ├── Admin.java
│   ├── Member.java
│   ├── Author.java
│   ├── Book.java
│   └── Borrowing.java
│
├── service
│   ├── AuthService.java
│   ├── LibraryService.java
│   └── ReportService.java
│
├── exception
│   ├── BookNotFoundException.java
│   ├── BookAlreadyBorrowedException.java
│   └── InvalidInputException.java
│
└── util
    ├── FileManager.java
    ├── InputValidator.java
    └── PasswordUtil.java
```

---

## Package Description

### `model`

Contains the main **data/model classes** of the system.

* `User.java` — Represents a system user.
* `Admin.java` — Represents an administrator.
* `Member.java` — Represents a library member.
* `Author.java` — Represents a book author.
* `Book.java` — Represents a library book.
* `Borrowing.java` — Represents a book borrowing record.

### `service`

Contains the **business logic** of the application.

* `AuthService.java` — Handles user login and authentication.
* `LibraryService.java` — Handles library operations such as adding, searching, borrowing, and returning books.
* `ReportService.java` — Generates library reports.

### `exception`

Contains **custom exception classes** used for error handling.

* `BookNotFoundException.java` — Used when a requested book cannot be found.
* `BookAlreadyBorrowedException.java` — Used when a user tries to borrow a book that is already borrowed.
* `InvalidInputException.java` — Used when the user provides invalid input.

### `util`

Contains **helper and utility classes**.

* `FileManager.java` — Handles saving and loading data using file I/O.
* `InputValidator.java` — Validates user input.
* `PasswordUtil.java` — Handles password-related utility operations.

### `Main.java`

The **entry point** of the application. It starts the program and displays the main menu/interface in the console.

---

## How to Run

### Step 1: Install Java

Make sure **JDK 17 or later** is installed on your computer.

### Step 2: Open the Project

Open the project in **IntelliJ IDEA**.

### Step 3: Check the Project Structure

Make sure all packages and Java files are located inside the `src` folder according to the project structure above.

### Step 4: Run the Application

Open:

```text
src → Main.java
```

Then run the `main()` method.

### Step 5: Use the System

Follow the instructions displayed in the **console** to:

* Log in
* Manage books
* Manage members
* Borrow and return books
* View borrowing records
* Generate reports

---

## Project Type

**Object-Oriented Programming (OOP) Course Project**

---

## watashiriad

**Student Project**
