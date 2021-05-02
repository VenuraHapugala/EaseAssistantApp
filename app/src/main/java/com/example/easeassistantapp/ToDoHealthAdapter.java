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

public class ToDoHealthAdapter extends ArrayAdapter<ToDoHealth> {

    private Context context;
    private int resource;
    List<ToDoHealth> todos;

    ToDoHealthAdapter(Context context, int resource, List<ToDoHealth> todos){
        super(context,resource,todos);
        this.context = context;
        this.resource = resource;
        this.todos = todos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView title = row.findViewById(R.id.titleh);
        TextView description = row.findViewById(R.id.descriptionh);
        ImageView imageView = row.findViewById(R.id.onGoingh);


        // todos [obj1,obj2,obj3]
        ToDoHealth toDo = todos.get(position);
        title.setText(toDo.getTitle());
        description.setText(toDo.getDescription());
        imageView.setVisibility(row.INVISIBLE);

        if(toDo.getFinished() > 0){
            imageView.setVisibility(View.VISIBLE);
        }
        return row;
    }
}
