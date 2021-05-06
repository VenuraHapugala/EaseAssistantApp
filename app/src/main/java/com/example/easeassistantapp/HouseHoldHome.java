package com.example.easeassistantapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HouseHoldHome extends AppCompatActivity {

    private FloatingActionButton add_hs;
    private ListView listView;
    private TextView count;
    private DbhandlerHousehold dbhandlerHousehold;
    private Context context;
    private List<HouseHoldModel> listings;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_hold_home);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        context = this;
        add_hs = findViewById(R.id.add_btn);
        listView = findViewById(R.id.hs_listview);
        count = findViewById(R.id.countlist);
        dbhandlerHousehold = new DbhandlerHousehold(context);
        listings = new ArrayList<>();

        listings = dbhandlerHousehold.getAllLists();

        HouseHoldAdapter adapter = new HouseHoldAdapter(context, R.layout.single_house_hold_list, listings);
        listView.setAdapter(adapter);
        //get list count
        int countLists = dbhandlerHousehold.countLists();
        count.setText("You have " + countLists + " listings");




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final HouseHoldModel houseHoldModel = listings.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(houseHoldModel.getTitle());
                builder.setMessage(houseHoldModel.getDescription());
                builder.setPositiveButton("FINISHED", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        houseHoldModel.setFinished(System.currentTimeMillis());
                        dbhandlerHousehold.hs_update_List(houseHoldModel);
                        startActivity(new Intent(context, HouseHoldHome.class));
                    }
                });
                builder.setNeutralButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, HouseHoldUpdate.class);
                        intent.putExtra("id", String.valueOf(houseHoldModel.getId()));
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbhandlerHousehold.hs_delete_List(houseHoldModel.getId());
                        startActivity(new Intent(context, HouseHoldHome.class));
                    }
                });
                builder.show();
            }
        });
       /* public void go2CreateHouseHold (View view){
            Intent intent = new Intent(context, HouseHoldCreate.class);
            startActivity(intent);
        }*/


    }

    public void go2CreateHouseHold(View view) {
        Intent intent=new Intent(context,HouseHoldCreate.class);
        startActivity(intent);
    }
}
