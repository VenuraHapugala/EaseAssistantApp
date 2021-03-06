package com.example.easeassistantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleText = title.getText().toString();
                String decText = des.getText().toString();
                updateDate = System.currentTimeMillis();
                if (TextUtils.isEmpty(title.getText())) {
                    title.setError("Enter Title");
                    title.requestFocus();
                }
                if (TextUtils.isEmpty(des.getText())) {
                    des.setError("Enter Description");
                    des.requestFocus();
                } else {

                    ToDoHealth toDoHealth = new ToDoHealth(Integer.parseInt(id), titleText, decText, updateDate, 0);
                    int state = dbHandler.updateSingleToDo(toDoHealth);
                    System.out.println(state);
                    Toast.makeText(EditToDoHealth.this, "Health Reminder updated successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(context, MainActivityHealth.class));

                }
            }
        });
    }
}
