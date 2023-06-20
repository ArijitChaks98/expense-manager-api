package com.example.ExpenseTrackerInitializedApiApplication.service;

import com.example.ExpenseTrackerInitializedApiApplication.entity.User;
import com.example.ExpenseTrackerInitializedApiApplication.entity.UserModel;
import com.example.ExpenseTrackerInitializedApiApplication.exceptions.ItemAlreadyExistsExceptions;
import com.example.ExpenseTrackerInitializedApiApplication.exceptions.ResourceNotFoundException;
import com.example.ExpenseTrackerInitializedApiApplication.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    private UserRepository userRepository;

    // Method Implementation for create user
    @Override
    public User createUser(UserModel user) {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new ItemAlreadyExistsExceptions("User is already register with email: " + user.getEmail());
        }
        User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    // Method Implementing for read the user input
    @Override
    public User readUser() {
        Long userId = getLoggedInUser().getId();
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found for the id: "+ userId));
    }

    // Method Implementing for updating the user service
    @Override
    public User updateUser(UserModel user) {
        User existingUser = readUser();
        existingUser.setName(user.getName() != null ? user.getName() : existingUser.getName());
        existingUser.setEmail(user.getEmail() !=null ? user.getEmail() : existingUser.getEmail());
        existingUser.setPassword(user.getPassword() !=null ? bcryptEncoder.encode(user.getPassword()) : existingUser.getPassword());
        existingUser.setAge(user.getAge() !=null ? user.getAge() : existingUser.getAge());
        return userRepository.save(existingUser);
    }
    // Method Implementing for deleting the user service
    @Override
    public void deleteUser() {
        User existingUser = readUser();
        userRepository.delete(existingUser);
    }
    // Method Implementing for creating loggedIn user id
    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found for the email: " + email));

    }
}
