package com.example.ExpenseTrackerInitializedApiApplication.repository;

import com.example.ExpenseTrackerInitializedApiApplication.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // Creating a finder for searching the values
    // For this method in the backend this query will run
    //SELECT * FROM expense_tkr WHERE user_id = '?' AND category = '?'
    Page<Expense> findByUserIdAndCategory(Long userId, String category, Pageable page);

    // Creating a finder for searching expanse name with their keyword
    // For this method in the backend this query will run
    // SELECT * FROM expense_tkr WHERE user_id = '?' name LIKE %KEYWORD%
    Page<Expense> findByUserIdAndNameContaining(Long userId, String keyword, Pageable page);

    // Creating a finder for searching expanse by its Date
    // For this method in the backend this query will run
    // SELECT * FROM expense_tkr WHERE user_id = '?' AND date BETWEEN 'startDate' AND 'endDate'
    Page<Expense> findByUserIdAndDateBetween(Long userId, Date startDate , Date endDate, Pageable page);

    // Creating a finder for searching expanse by its User ID
    // For this method in the backend this query will run
    // SELECT * FROM expense_tkr WHERE user_id = '?'
    Page<Expense> findByUserId(Long userId, Pageable page);

    // Creating a finder for searching expanse by its User ID and Expense ID
    // For this method in the backend this query will run
    // SELECT * FROM expense_tkr WHERE user_id = '?' AND id = '?'
    Optional<Expense> findByUserIdAndId(Long userId, Long expenseId);
}
