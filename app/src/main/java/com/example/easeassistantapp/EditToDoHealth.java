package com.example.easeassistantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditToDoHealth extends AppCompatActivity {

    private EditText title,des;
    private Button edit;
    private DbHandlerHealth dbHandler;
    private Context context;
    private Long updateDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_to_do_health);

        context = this;
        dbHandler = new DbHandlerHealth(context);
        title = findViewById(R.id.editToDoTextTitleh);
        des = findViewById(R.id.editToDoTextDescriptionh);
        edit = findViewById(R.id.buttonEdith);

        final String id = getIntent().getStringExtra("id");
        ToDoHealth todo = dbHandler.getSingleToDo(Integer.parseInt(id));

        title.setText(todo.getTitle());
        des.setText(todo.getDescription());
    }
}