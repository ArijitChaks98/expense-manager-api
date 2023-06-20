package com.example.ExpenseTrackerInitializedApiApplication.service;

import com.example.ExpenseTrackerInitializedApiApplication.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;

public interface ExpenseService {
    // Creating a method for Pagination
    Page<Expense> getAllExpenses(Pageable page);

    // Creating a second method
    Expense getExpenseById(Long id);

    // Creating a method for deleting
    void deleteExpenseById(Long id);

    // Creating a method (Rest End Point) for save the expense details in database
    Expense saveExpenseDetails(Expense expense);

    // Creating a method (Rest End Point) for update the expense details
    Expense updateExpenseDetails(Long id, Expense expense );

    // Creating a method for searching the values
    List<Expense> readByCategory(String category, Pageable page);

    // Creating a method for searching expanse name with their keyword
    List<Expense> readByName(String keyword, Pageable page);

    // Creating a method for searching expanse by its Date
    List<Expense> readByDate(Date startDate, Date endDate, Pageable page);
}
