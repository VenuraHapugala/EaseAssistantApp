package com.example.easeassistantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.easeassistontapp.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ReminderActivity extends AppCompatActivity {

    EditText date, time;
    TextView remaningView, hourView;
    Button calculate;

//    String dateEntered  = "02/06/1998";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        date = findViewById(R.id.Date);
        remaningView = findViewById(R.id.textView2);
        calculate = findViewById(R.id.calcuate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                Calendar user_selected_day = new GregorianCalendar();

                Calendar today = new GregorianCalendar();

                Date selected_date = null;

                try {

                    selected_date = formatter.parse(date.getText().toString());

                } catch (ParseException e) {

                    e.printStackTrace();

                }

                if (selected_date != null) {
                    user_selected_day.setTime(selected_date);
                }

                int user_selected_month = user_selected_day.get(Calendar.MONTH);
                int CMonth = today.get(Calendar.MONTH);

                int user_selected_date = user_selected_day.get(Calendar.DAY_OF_MONTH);
                int CDate = today.get(Calendar.DAY_OF_MONTH);

                user_selected_day.set(Calendar.YEAR, today.get(Calendar.YEAR));

                user_selected_day.set(Calendar.HOUR_OF_DAY,today.get(Calendar.HOUR_OF_DAY));
                user_selected_day.set(Calendar.MINUTE,today.get(Calendar.MINUTE));
                user_selected_day.set(Calendar.SECOND,today.get(Calendar.SECOND));
                user_selected_day.set(Calendar.MILLISECOND,today.get(Calendar.MILLISECOND));


                if (user_selected_month < CMonth) {

                    user_selected_day.set(Calendar.YEAR,today.get(Calendar.YEAR) + 1);

                }

                else if (user_selected_month == CMonth){

                    if(user_selected_date < CDate){
                        user_selected_day.set(Calendar.YEAR,today.get(Calendar.YEAR) + 1);
                    }

                }

                long millis = user_selected_day.getTimeInMillis() - today.getTimeInMillis();
                long days;
                long hours;
                String textDay;
                String textHour;

                if (user_selected_day.getTime() == today.getTime()){

                    days = 0;
                    hours = 0;

                }
                else {
                    days = (millis / (24 * 60 * 60 * 1000));
                }

                textDay = String.valueOf(days);

                remaningView.setText(textDay);

            }
        });


    }
}