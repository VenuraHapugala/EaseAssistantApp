package com.example.easeassistontapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class addNote extends AppCompatActivity {

    Button addBtn;
    DBHelper dbHelper;
    RecyclerView rvPrograms;
    NoteAdapter noteAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<NoteDetails> noteDetailsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);


        dbHelper = new DBHelper(this);

        noteDetailsList = dbHelper.getAllData();
        rvPrograms  = findViewById(R.id.rvPrograms);
        rvPrograms.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvPrograms.setLayoutManager(layoutManager);
        noteAdapter = new NoteAdapter(this, noteDetailsList, rvPrograms);
        rvPrograms.setAdapter(noteAdapter);

        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), NoteActivity.class);
                startActivity(intent);
            }
        });

        rvPrograms.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//                Toast.makeText(context, item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }
}