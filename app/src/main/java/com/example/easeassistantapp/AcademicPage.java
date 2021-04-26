package com.example.easeassistantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AcademicPage extends AppCompatActivity {

    Button noteBtn, reminderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_page);


        noteBtn = findViewById(R.id.notebtn);

        noteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AcademicaddNote.class);
                startActivity(intent);
            }
        });

        reminderBtn = findViewById(R.id.reminderBtn);

        reminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AcademicReminderActivity.class);
                startActivity(intent);
            }
        });




    }
}