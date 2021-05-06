package com.example.easeassistantapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class HouseHoldAdapter extends ArrayAdapter<HouseHoldModel>{

    private Context context;
    private int resource;
    List<HouseHoldModel> listings;

    HouseHoldAdapter(Context context, int resource, List<HouseHoldModel> listings){
        super(context,resource,listings);
        this.context = context;
        this.resource = resource;
        this.listings = listings;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView title = row.findViewById(R.id.single_list_title);
        TextView description = row.findViewById(R.id.single_list_desc);
        ImageView imageView = row.findViewById(R.id.list_onGoing);



        HouseHoldModel houseHoldModel = listings.get(position);
        title.setText(houseHoldModel.getTitle());
        description.setText(houseHoldModel.getDescription());
        imageView.setVisibility(row.INVISIBLE);

        if(houseHoldModel.getFinished() > 0){
            imageView.setVisibility(View.VISIBLE);
        }
        return row;
    }

}

