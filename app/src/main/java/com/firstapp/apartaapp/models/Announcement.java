package com.firstapp.apartaapp.models;

public class Announcement {

    // Announcement title
    private String title;

    // Announcement message
    private String message;

    // Date created
    private String created_at;

    // Empty constructor for Retrofit
    public Announcement() {
    }

    // Constructor for temporary UI testing
    public Announcement(String title, String created_at, String message) {
        this.title = title;
        this.created_at = created_at;
        this.message = message;
    }

    // Get title
    public String getTitle() {
        return title;
    }

    // Get message
    public String getMessage() {
        return message;
    }

    // Get created date
    public String getCreated_at() {
        return created_at;
    }
}