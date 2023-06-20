package com.example.ExpenseTrackerInitializedApiApplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ItemAlreadyExistsExceptions extends RuntimeException{
    // Serial Version ID
    private static final long serialVersionUID = 1L;

    // Creating a parameterized constructor
    public ItemAlreadyExistsExceptions(String message){
        super(message);
    }
}
