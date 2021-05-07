package com.example.easeassistantapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AcademicNoteActivity extends AppCompatActivity {

    EditText name, note;
    Button save, view,update,delete;
    AcademicDBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_note);

        name =  findViewById(R.id.name);
        note = findViewById(R.id.note);

        save = findViewById(R.id.btnsave);

        DB = new AcademicDBHelper( this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String noteTXT = note.getText().toString();

                Boolean checksavedata =  DB.savedata(nameTXT, noteTXT);
                if(checksavedata == true) {
                    Toast.makeText(AcademicNoteActivity.this, "Note Saved Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), AcademicaddNote.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(AcademicNoteActivity.this, "Saving Note Failed, Try Again!", Toast.LENGTH_SHORT).show();
            }
        });







    }
}
