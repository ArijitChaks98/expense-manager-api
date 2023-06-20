package com.example.ExpenseTrackerInitializedApiApplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

// Here we are creating Entity class for User
// Adding annotations
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users_expense")
public class User {
    // Declaring Variables for fetching all the data from DB
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    private Long age;
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    // Here we are just updating the table with create and update date time column
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

}
