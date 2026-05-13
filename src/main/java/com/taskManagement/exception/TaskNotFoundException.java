package com.taskManagement.exception;

public class TaskNotFoundException
        extends RuntimeException {

    // Custom exception class
    // Used when expense is not found in DB

    public TaskNotFoundException(String message) {

        // Constructor used to pass custom error message

        super(message);

        // Calls parent RuntimeException constructor
        // Stores error message internally
    }
}
