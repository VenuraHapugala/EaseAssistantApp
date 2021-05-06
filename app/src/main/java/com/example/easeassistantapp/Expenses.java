package com.example.easeassistantapp;

import android.widget.RadioGroup;

public class Expenses {
    private int id;
    private String details,amount,type;
    private long date;

    public Expenses() {

    }

    public Expenses(int id, String details, String amount, String type, long date) {
        this.id = id;
        this.details = details;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    public Expenses(String details, String amount, String type, long date) {
        this.details = details;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
