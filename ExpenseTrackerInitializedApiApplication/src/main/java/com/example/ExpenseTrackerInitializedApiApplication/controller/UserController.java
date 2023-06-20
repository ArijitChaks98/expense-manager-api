package com.example.ExpenseTrackerInitializedApiApplication.controller;

import com.example.ExpenseTrackerInitializedApiApplication.entity.User;
import com.example.ExpenseTrackerInitializedApiApplication.entity.UserModel;
import com.example.ExpenseTrackerInitializedApiApplication.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    // Creating rest end for Read User
    @GetMapping("/profile")
    public ResponseEntity<User> readUser(){
        return new ResponseEntity<User>(userService.readUser(), HttpStatus.OK);
    }

    // Creating rest end for Updating the user
    @PutMapping("/profile")
    public ResponseEntity<User> updateUser(@RequestBody UserModel user){
        return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
    }

    // Creating rest end for Deleting the user
    @DeleteMapping("/deactivate")
    public ResponseEntity<HttpStatus> deleteUSer(){
        userService.deleteUser();
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
