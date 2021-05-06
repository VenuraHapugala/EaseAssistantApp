package com.example.easeassistantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

public class EditExpenses extends AppCompatActivity {

    EditText details,amount,type;
    DatePicker date;
    Button edit;
    private expDbHandler expDbHandler;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expenses);

        context =this;
        expDbHandler =new expDbHandler(context);

        details = findViewById(R.id.editexpDetails);
        amount = findViewById(R.id.editexpAmount);
        type = findViewById(R.id.editexpType);
        date = findViewById(R.id.editexpDate);
        edit = findViewById(R.id.expEdit);

        final String id = getIntent().getStringExtra("id");
        Expenses expenses = expDbHandler.getSingleEXP(Integer.parseInt(id));

        details.setText(expenses.getDetails());
        amount.setText(expenses.getAmount());
        type.setText(expenses.getType());
        date.setMaxDate(expenses.getDate());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editDetails = details.getText().toString();
                String editAmount = amount.getText().toString();
                String editType = type.getText().toString();
                Long editDate = date.getMaxDate();

                Expenses exp = new Expenses(Integer.parseInt(id),editDetails,editAmount,editType,editDate);
                int state = expDbHandler.updateExp(exp);

                startActivity(new Intent(context,ExpHome.class));
            }
        });
    }
}