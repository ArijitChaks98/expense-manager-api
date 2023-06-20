package com.example.ExpenseTrackerInitializedApiApplication.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.apache.logging.log4j.message.Message;
import org.aspectj.bridge.IMessage;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

// Here we are creating Entity class for Expense
// Adding annotations
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "expense_tkr")
public class Expense {
    // Declaring Variables for fetching all the data from DB
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "expense_name")
    @NotBlank(message = "Expense name must not be null")
    @Size(min = 3, message = "Expanse name should be more than 3 latter's")
    private String name;
    @Column(name = "expense_desc")
    private String description;
    @Column(name = "expense_amount")
    @NotNull(message = "Expanse amount should not be null")
    private BigDecimal amount;
    @NotBlank(message = "Category should not be null")
    private String category;
    @NotNull(message = "Date must not be null")
    private Date date;
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    // Here we are just updating the table with create and update date time column
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    // Here we are creating a table named user_id and map user_id with user_expense
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
}
