package com.example.ExpenseTrackerInitializedApiApplication.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    // Serial Version ID
    private static final long serialVersionUID = 1L;

    // Creating a parameterized constructor
    public ResourceNotFoundException(String message){
        super(message);
    }

}
