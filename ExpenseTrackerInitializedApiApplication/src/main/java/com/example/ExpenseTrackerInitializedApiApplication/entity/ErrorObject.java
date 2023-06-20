package com.example.ExpenseTrackerInitializedApiApplication.entity;

import lombok.Data;

import java.util.Date;
@Data
public class ErrorObject {
    // Declaring variable for custom error message
    private Integer statusCode;
    private String message;
    private Date timeStamp;
}
