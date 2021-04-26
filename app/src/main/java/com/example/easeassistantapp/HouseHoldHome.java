package com.example.easeassistantapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HouseHoldHome extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_hold_home);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


         /* public void onClick(View view){

        Intent i = new Intent(this, create.class);
        startActivity(i);

        Intent t = new Intent(this, update.class);
        startActivity(t);

        Intent j = new Intent(this, delete.class);
        startActivity(j);
    } */
        public void go2CreateHouseHold(View view){
            Intent intent=new Intent(this,HouseHoldCreate.class);
            startActivity(intent);
        }

        public void go2EditHouseHold(View view){
            Intent intent=new Intent(this,HouseHoldUpdate.class);
            startActivity(intent);
        }

        public void go2DeleteHouseHold(View view){
            Intent intent=new Intent(this,HouseHoldDelete.class);
            startActivity(intent);


    }
}