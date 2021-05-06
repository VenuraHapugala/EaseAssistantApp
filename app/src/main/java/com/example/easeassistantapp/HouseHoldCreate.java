package com.example.easeassistantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HouseHoldCreate extends AppCompatActivity {

    private EditText title, desc;
    private Button add;
    private DbhandlerHousehold dbhandlerHousehold;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_hold_create);

        title = findViewById(R.id.textInputTitleText);
        desc = findViewById(R.id.textInputDescText);
        add = findViewById(R.id.create_btn);
        context = this;

        dbhandlerHousehold = new DbhandlerHousehold(context);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hs_userTitle = title.getText().toString();
                String hs_userDesc = desc.getText().toString();
                long started = System.currentTimeMillis();

                HouseHoldModel houseHoldModel = new HouseHoldModel(hs_userTitle,hs_userDesc,started,0);
                dbhandlerHousehold.addList(houseHoldModel);

                startActivity(new Intent(context,HouseHoldHome.class));
            }
        });

    }
}