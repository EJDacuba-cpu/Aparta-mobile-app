package com.firstapp.apartaapp.models;

public class MaintenanceRequest {
    private String title;
    private String status; // "Pending", "In Progress", "Resolved"
    private String date;
    private String description;

    public MaintenanceRequest(String title, String status, String date, String description) {
        this.title = title;
        this.status = status;
        this.date = date;
        this.description = description;
    }

    public String getTitle() { return title; }
    public String getStatus() { return status; }
    public String getDate() { return date; }
    public String getDescription() { return description; }
}