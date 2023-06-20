package com.example.ExpenseTrackerInitializedApiApplication.service;

import com.example.ExpenseTrackerInitializedApiApplication.entity.User;
import com.example.ExpenseTrackerInitializedApiApplication.entity.UserModel;

public interface UserService {
    // Creating method for getting the user
    User createUser (UserModel user);

    // Creating a method for read the user input
    User readUser();

    // Creating a method for updating the user service
    User updateUser(UserModel user);

    // Creating method for deleting the user service
    void deleteUser();

    // Creating a method for loggedIn user id
    User getLoggedInUser();
}
