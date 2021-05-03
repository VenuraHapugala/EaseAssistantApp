package com.example.easeassistantapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivityHealth extends AppCompatActivity {

    private Button add;
    private ListView listView;
    private TextView count;
    private DbHandlerHealth dbHandler;
    Context context;
    private List<ToDoHealth> toDos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_health);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        context = this;


        dbHandler = new DbHandlerHealth(context);
        add = findViewById(R.id.addh);
        listView = findViewById(R.id.todolisth);
        count = findViewById(R.id.todocounth);
        toDos = new ArrayList<>();

        toDos = dbHandler.getAllToDos();
        ToDoHealthAdapter adapter = new ToDoHealthAdapter(context,R.layout.single_todo_health,toDos);

        listView.setAdapter(adapter);
        //get todo counts from the table
        int countTodo = dbHandler.countToDo();
        count.setText("You have "+countTodo+" todos");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,AddToDoHealth.class));
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final ToDoHealth toDoHealth = toDos.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(toDoHealth.getTitle());
                builder.setMessage(toDoHealth.getDescription());
                builder.setPositiveButton("Finished", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toDoHealth.setFinished(System.currentTimeMillis());
                        dbHandler.updateSingleToDo(toDoHealth);
                        startActivity(new Intent(context,MainActivityHealth.class));
                    }
                });


                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHandler.deleteToDo(toDoHealth.getId());
                        startActivity(new Intent(context,MainActivityHealth.class));
                    }
                });


                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context,EditToDoHealth.class);
                        intent.putExtra("id",String.valueOf(toDoHealth.getId()));
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });

    }
}