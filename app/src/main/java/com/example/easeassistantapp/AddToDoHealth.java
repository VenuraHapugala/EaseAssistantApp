package com.example.easeassistantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddToDoHealth extends AppCompatActivity {

    private EditText title, desc;
    private Button add;
    private DbHandlerHealth dbHandlerHealth;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do_health);

        title = findViewById(R.id.editTextTitleh);
        desc = findViewById(R.id.editTextDescriptionh);
        add = findViewById(R.id.buttonAddh);
        context=this;

        dbHandlerHealth=new DbHandlerHealth(context);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userTitle = title.getText().toString();
                String userDesc = desc.getText().toString();
                long started = System.currentTimeMillis();

                ToDoHealth toDoHealth = new ToDoHealth(userTitle,userDesc,started,0);
                dbHandlerHealth.addToDo(toDoHealth);

                startActivity(new Intent(context,MainActivity.class));
            }
        });


    }
}