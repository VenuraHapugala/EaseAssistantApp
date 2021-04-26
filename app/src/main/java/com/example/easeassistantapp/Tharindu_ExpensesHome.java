package com.example.easeassistantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tharindu_ExpensesHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tharindu__expenses_home);



    Button b = (Button)findViewById(R.id.deleteButton);
        b.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Tharindu_ExpensesHome.this,Tharindu_DeleteExpenses.class));
        }
    });

}


    public void g02ExpenseCreatePage(View view){
        Intent intent=new Intent(this,Tharindu_AddExpenses.class);
        startActivity(intent);
    }
}