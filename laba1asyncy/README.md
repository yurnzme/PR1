# Library System Simulation

This project simulates a library system where multiple students can try to borrow and return books. The library has limited book availability, and students can only borrow books if they are available. The library is only open during certain hours, and students attempt to use it during the open hours.

## Features

- **Concurrency**: Simulates multiple students interacting with the library system concurrently using Java's `Semaphore` and `ExecutorService`.
- **Book Availability**: The library has a fixed number of books, and students must wait if no books are available.
- **Library Hours**: The library operates only between 9:00 AM and 5:00 PM. Outside these hours, students cannot access the library.

## Classes

### `LibrarySystem`

- **Main Method**: The main entry point of the program where multiple students (represented as threads) are created. Each student will attempt to borrow and return a book.
- **Concurrency**: Uses `ExecutorService` to handle multiple threads and `Semaphore` to control access to the limited number of books.
- **Library Open Check**: The library's working hours are checked using the `isLibraryOpen()` method.

### Methods

- **`main(String[] args)`**: Initializes the `ExecutorService` and submits tasks for each student to interact with the library.
- **`isLibraryOpen()`**: Checks if the current time is within the library's open hours (9 AM to 5 PM).
- **`borrowBook(int studentId)`**: Simulates borrowing a book. If a book is available, it is borrowed; otherwise, the student is informed that no books are available.
- **`returnBook(int studentId)`**: Simulates returning a book, freeing up a slot for another student.

## Configuration

- **Working Hours**: The library is open from 9 AM to 5 PM, controlled by the constants `WORKING_HOURS_START` and `WORKING_HOURS_END`.
- **Books Available**: The number of books available in the library is controlled by a `Semaphore` with a limit of 3.
- **Testing Time**: The `currentHour` is set manually for testing purposes. Replace it with `LocalTime.now().getHour()` for real-time operation.

## Running the Program

To run the program:

1. Ensure that Java is installed on your machine.
2. Compile the program using `javac`:
   ```bash
   javac LibrarySystem.java
