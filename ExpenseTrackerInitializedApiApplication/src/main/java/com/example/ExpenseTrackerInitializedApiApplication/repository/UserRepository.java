package com.example.ExpenseTrackerInitializedApiApplication.repository;

import com.example.ExpenseTrackerInitializedApiApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Creating a method for custom exception
    boolean existsByEmail(String email);

    // Creating a finder method for adding the details to database
    Optional<User> findByEmail(String email);
}
