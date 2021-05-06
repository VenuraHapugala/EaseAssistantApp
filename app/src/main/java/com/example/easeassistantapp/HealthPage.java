package com.example.easeassistantapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HealthPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_page);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void go2BMIPage(View view){
        Intent intent=new Intent(this, BMIhealthPage.class);
        startActivity(intent);
    }

    public void go2HealthNoticesAndImagesPage(View view){
        Intent intent=new Intent(this,MainActivityHealth.class);
        startActivity(intent);
    }
}