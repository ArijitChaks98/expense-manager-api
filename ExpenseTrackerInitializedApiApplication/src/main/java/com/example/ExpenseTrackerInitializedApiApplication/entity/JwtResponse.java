package com.example.ExpenseTrackerInitializedApiApplication.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse {
    // This will hold the Jwt Token
    private final String jwtToken;
}
