package com.example.easeassistontapp;

public class NoteDetails {

    private String note;
    private String title;

    public NoteDetails(String note, String title) {
        this.note = note;
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public String getTitle() {
        return title;
    }
}
