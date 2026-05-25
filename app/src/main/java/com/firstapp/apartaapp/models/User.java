package com.firstapp.apartaapp.models;

// User model from Supabase users table
public class User {

    // User full name
    private String full_name;

    // User email
    private String email;

    // Get full name
    public String getFull_name() {
        return full_name;
    }

    // Get email
    public String getEmail() {
        return email;
    }
}