package com.example.easeassistantapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ExpHome extends AppCompatActivity {

    private Button add;
    private ListView listView;
    private TextView textView;
    private expDbHandler dbHandler;
    private AddExpenses addExpenses;
    private List<Expenses> exP;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp_home);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);

        context = this;
        dbHandler = new expDbHandler(context);
        addExpenses = new AddExpenses();
        add = findViewById(R.id.expAdd);
        listView = findViewById(R.id.expList);
        textView = findViewById(R.id.expBalance);
        exP = new ArrayList<>();
        Expenses exx = new Expenses();

        exP = dbHandler.getAllExpenses();
        ExpAdapter expAdapter = new ExpAdapter(context,R.layout.single_expense,exP);


        listView.setAdapter(expAdapter);

        /*float EXPBalance = addExpenses.balanceExp();
        textView.setText("Rs "+EXPBalance);*/


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Expenses expenses = exP.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(expenses.getDetails());
                builder.setMessage(expenses.getAmount());

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHandler.deleteExp(expenses.getId());
                        startActivity(new Intent(context,ExpHome.class));
                        Toast.makeText(context,"Deleted Successfully", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context,EditExpenses.class);
                        intent.putExtra("id",String.valueOf(expenses.getId()));
                        startActivity(intent);

                    }
                });
                builder.show();
            }
        });

    }

    public void g02ExpenseCreatePage(View view){
        Intent intent=new Intent(this,AddExpenses.class);
        startActivity(intent);
    }
}