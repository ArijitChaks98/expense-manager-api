package com.example.ExpenseTrackerInitializedApiApplication.controller;

import com.example.ExpenseTrackerInitializedApiApplication.entity.Expense;
import com.example.ExpenseTrackerInitializedApiApplication.service.ExpenseService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.env.ConfigTreePropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

// RestController will contain Response Body as well as Controller which contain Components.
@RestController
public class ExpenseController {
    // Here we are creating a method which will come in the web-browser
    @Autowired
    private ExpenseService expenseService;
    //Here we are using pagination in api for maintaining the data
    @GetMapping("/expenses")
    public Page<Expense> getAllExpenses (Pageable page){
        // Adding this change for creating a custom exception
        // int number = 1;
        //  calculateFactorial(number);
        return expenseService.getAllExpenses(page);
    }
    // Here we are passing the parameter in the URL. By Using path variables.
    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable("id") Long id){
        return expenseService.getExpenseById(id);
    }
    //Here we are passing the parameter in the URL. By Using query string.
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses")
    public void deleteExpensesById(@RequestParam("id") Long id){
        expenseService.deleteExpenseById(id);
    }
    // Here I'm Mapping the HTTP request body to the Java Object
    @ResponseStatus(value = HttpStatus.CREATED) // This is for changes the response status code in post-man
    @PostMapping("/expenses")
    public Expense saveExpenseDetails(@Valid @RequestBody Expense expense){
        return expenseService.saveExpenseDetails(expense);
    }
    // Here I'm updating the expense detail
    @PutMapping("/expenses/{id}")
    public Expense updateExpenseDetails(@RequestBody Expense expense, @PathVariable("id") Long id){
        return expenseService.updateExpenseDetails(id, expense);
    }
    // Here I'm searching the categories by values
    @GetMapping("/expenses/category")
    public List<Expense> getExpensesByCategory(@RequestParam String category, Pageable page){
        return expenseService.readByCategory(category, page);
    }
    // Here I'm searching the names by keyword
    @GetMapping("/expenses/name")
    public List<Expense> getExpenseByName(@RequestParam String keyword, Pageable page){
        return expenseService.readByName(keyword, page);
    }
    // Here I'm searching expanse by its Date
    @GetMapping("/expenses/date")
    public List<Expense> getExpenseByDate(@RequestParam (required = false) Date startDate,
                                          @RequestParam (required = false) Date endDate,
                                          Pageable page){
        return expenseService.readByDate(startDate, endDate, page);
    }

    // Adding this change for creating an exception
    //public int calculateFactorial(int number ){
    //    return number*calculateFactorial(number-1);
    //}
}
