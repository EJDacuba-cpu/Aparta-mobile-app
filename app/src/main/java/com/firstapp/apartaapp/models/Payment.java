package com.firstapp.apartaapp.models;

public class Payment {
    private String month;
    private String amount;
    private String date;
    private String status; // "Paid", "Unpaid", "Late"

    public Payment(String month, String amount, String date, String status) {
        this.month = month;
        this.amount = amount;
        this.date = date;
        this.status = status;
    }

    public String getMonth() { return month; }
    public String getAmount() { return amount; }
    public String getDate() { return date; }
    public String getStatus() { return status; }
}