package com.example.easeassistantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void go2HealthPage(View view){
        Intent intent=new Intent(this,HealthPage.class);
        startActivity(intent);
    }

    public void go2AcademicPage(View view){
        Intent intent=new Intent(this,AcademicPage.class);
        startActivity(intent);
    }

    public void g02HouseHoldPage(View view){
        Intent intent=new Intent(this,HouseHoldHome.class);
        startActivity(intent);
    }

    public void g02ExpensesPage(View view){
        Intent intent=new Intent(this,Tharindu_ExpensesHome.class);
        startActivity(intent);
    }
}