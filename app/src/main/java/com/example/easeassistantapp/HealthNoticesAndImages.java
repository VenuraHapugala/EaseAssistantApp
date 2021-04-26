package com.example.easeassistantapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HealthNoticesAndImages extends AppCompatActivity {

    private Object ImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_notices_and_images);
        android.widget.ImageButton b =(ImageButton) findViewById(R.id.imageButton);

        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HealthNoticesAndImages.this ,DeleteHealthNotices.class));
            }
        });

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    public void go2EditHealthNoticesPage(View view){
        Intent intent=new Intent(this,EditHealthNotices.class);
        startActivity(intent);
    }

    public void go2CreateHealthNoticesPage(View view){
        Intent intent=new Intent(this,CreateHealthNotices.class);
        startActivity(intent);
    }

}

