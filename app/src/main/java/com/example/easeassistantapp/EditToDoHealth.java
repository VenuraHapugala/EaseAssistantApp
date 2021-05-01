package com.example.easeassistantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditToDoHealth extends AppCompatActivity {

    private EditText title,des;
    private Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_to_do_health);

        title = findViewById(R.id.editToDoTextTitleh);
        des = findViewById(R.id.editToDoTextDescriptionh);
        edit = findViewById(R.id.buttonEdith);

    }
}