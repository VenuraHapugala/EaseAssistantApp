package com.example.easeassistantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivityHealth extends AppCompatActivity {

    private Button add;
    private ListView listView;
    private TextView count;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_health);

        add = findViewById(R.id.addh);
        listView = findViewById(R.id.todolisth);
        count = findViewById(R.id.todocounth);
        context = this;

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,AddToDoHealth.class));
            }
        });


    }
}