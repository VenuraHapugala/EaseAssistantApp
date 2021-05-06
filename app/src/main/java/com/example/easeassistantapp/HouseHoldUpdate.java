package com.example.easeassistantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HouseHoldUpdate extends AppCompatActivity {

    private EditText title,des;
    private Button edit;
    private DbhandlerHousehold dbhandlerHousehold;
    private Context context;
    private Long updateDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_hold_update);

        context = this;
        dbhandlerHousehold = new DbhandlerHousehold(context);

        title = findViewById(R.id.update_list_title);
        des = findViewById(R.id.update_list_desc);
        edit = findViewById(R.id.update_btn);

        final String id = getIntent().getStringExtra("id");
        HouseHoldModel houseHoldModel = dbhandlerHousehold.getSingleList(Integer.parseInt(id));

        title.setText(houseHoldModel.getTitle());
        des.setText(houseHoldModel.getDescription());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleText = title.getText().toString();
                String decText = des.getText().toString();
                updateDate = System.currentTimeMillis();

                HouseHoldModel houseHoldModel = new HouseHoldModel(Integer.parseInt(id),titleText,decText,updateDate,0);
                int state = dbhandlerHousehold.hs_update_List(houseHoldModel);
                System.out.println(state);
                startActivity(new Intent(context,HouseHoldHome.class));
            }
        });


    }
}