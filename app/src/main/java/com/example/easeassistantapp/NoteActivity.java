package com.example.easeassistantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easeassistontapp.R;

public class NoteActivity extends AppCompatActivity {

    EditText name, note;
    Button save, view,update,delete;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        name =  findViewById(R.id.name);
        note = findViewById(R.id.note);

        save = findViewById(R.id.btnsave);

        DB = new DBHelper( this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String noteTXT = note.getText().toString();

                Boolean checksavedata =  DB.savedata(nameTXT, noteTXT);
                if(checksavedata == true) {
                    Toast.makeText(NoteActivity.this, "save your note", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), addNote.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(NoteActivity.this, "not save note", Toast.LENGTH_SHORT).show();
            }
        });







    }
}