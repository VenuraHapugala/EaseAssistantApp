package com.example.easeassistantapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ExpAdapter extends ArrayAdapter<Expenses> {

    private Context context;
    private int resource;
    List<Expenses> exP;

    ExpAdapter(Context context,int resource,List<Expenses> exP){
        super(context,resource,exP);
        this.context = context;
        this.resource=resource;
        this.exP=exP;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View row = layoutInflater.inflate(resource,parent,false);

        TextView details = row.findViewById(R.id.details);
        TextView amount = row.findViewById(R.id.amount);

        //getting array values one by one by index
        Expenses expenses = exP.get(position);
        details.setText(expenses.getDetails());
        amount.setText(expenses.getAmount());

        return row;

    }
}
