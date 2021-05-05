package com.example.easeassistantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easeassistontapp.R;

public class activity_update extends AppCompatActivity {

    String noteTitle = "";
    String noteTitleNew = "ex", noteText = "ex2";
    EditText title, note;
    Button update, delete;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title = findViewById(R.id.btntitle);
        note = findViewById(R.id.btnnote);
        update = findViewById(R.id.btnupdate);
        delete = findViewById(R.id.btndelete);

        DB = new DBHelper( this);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                noteTitle = null;
            } else {
                noteTitle = extras.getString("noteTitle");
            }
        } else {
            noteTitle = (String) savedInstanceState.getSerializable("noteTitle");
        }

        Cursor res = DB.getdata(noteTitle);
        if(res.getCount()==0){
            Toast.makeText(activity_update.this, "no Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
               noteTitleNew = res.getString(  0);
               noteText = res.getString(  1);
        }

        title.setText(noteTitleNew);
        title.setEnabled(false);
        note.setText(noteText);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = title.getText().toString();
                String noteTXT = note.getText().toString();

                Boolean checkupdatedata = DB.updateuserdate(nameTXT, noteTXT);

                if(checkupdatedata == true) {
                    Toast.makeText(activity_update.this, "Entry  update", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), addNote.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(activity_update.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = title.getText().toString();

                Boolean checkdeletedata = DB.deletedata(nameTXT);

                if(checkdeletedata == true) {
                    Toast.makeText(activity_update.this, "Entry delete", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), addNote.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(activity_update.this, " Entry Not deleted", Toast.LENGTH_SHORT).show();
            }
        });

//        Toast.makeText(getBaseContext(), noteTitle, Toast.LENGTH_SHORT).show();
    }
}