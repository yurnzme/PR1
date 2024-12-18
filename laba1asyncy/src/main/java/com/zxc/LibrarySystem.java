package com.zxc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LibrarySystem {

    private static final int WORKING_HOURS_START = 9;
    private static final int WORKING_HOURS_END = 17;

    private static final Semaphore booksSemaphore = new Semaphore(3);
    private static int currentHour = 10;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 5; i++) {
            int studentId = i;
            executorService.submit(() -> {
                try {
                    if (isLibraryOpen()) {
                        borrowBook(studentId);
                        Thread.sleep((long) (Math.random() * 5000));
                        returnBook(studentId);
                    } else {
                        System.out.println("Student " + studentId + " cannot use the library: closed.");
                    }
                } catch (InterruptedException e) {
                    System.out.println("An error occurred for student " + studentId + ": " + e.getMessage());
                }
            });
        }

        executorService.shutdown();
    }

    private static boolean isLibraryOpen() {
        return currentHour >= WORKING_HOURS_START && currentHour < WORKING_HOURS_END;
    }

    private static void borrowBook(int studentId) throws InterruptedException {
        System.out.println("Student " + studentId + " is trying to borrow a book...");
        if (booksSemaphore.tryAcquire()) {
            System.out.println("Student " + studentId + " borrowed a book.");
        } else {
            System.out.println("Student " + studentId + " could not borrow a book: no books available.");
        }
    }

    private static void returnBook(int studentId) {
        System.out.println("Student " + studentId + " is returning a book...");
        booksSemaphore.release();
        System.out.println("Student " + studentId + " returned a book.");
    }
}
