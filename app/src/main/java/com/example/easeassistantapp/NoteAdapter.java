package com.example.easeassistantapp;


import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easeassistontapp.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    Context context;
    List<NoteDetails> contactsList;
    RecyclerView rvPrograms;

    final View.OnClickListener onClickListener = new MyOnClickListner();

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView note;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            note = itemView.findViewById(R.id.item_note);
        }
    }

    public NoteAdapter(Context context, List<NoteDetails> contactsList, RecyclerView rvPrograms){

        this.context = context;
        this.contactsList = contactsList;
        this.rvPrograms = rvPrograms;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item, parent, false);

        view.setOnClickListener(onClickListener);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder viewHolder, int i) {
        NoteDetails noteDetails = contactsList.get(i);

        viewHolder.title.setText(noteDetails.getTitle());
        viewHolder.note.setText(noteDetails.getNote());
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }


    private class MyOnClickListner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int itemPosition = rvPrograms.getChildLayoutPosition(v);
            String item = contactsList.get(itemPosition).getTitle();
//            Toast.makeText(context, item, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, activity_update.class);
            intent.putExtra("noteTitle", item);
            context.startActivity(intent);

        }
    }
}
