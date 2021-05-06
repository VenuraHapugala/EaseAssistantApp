package com.example.easeassistantapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BMIhealthPage extends AppCompatActivity {
    EditText weight,height;
    TextView resulttext;
    String calculation,BMIresult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_page);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        weight=findViewById(R.id.editTextTextPersonName2);
        height=findViewById(R.id.editTextTextPersonName4);
        resulttext=findViewById(R.id.textView3);



    }

    public void calculateBMI(View view) {
        String S1=weight.getText().toString();
        String S2=height.getText().toString();
        if(TextUtils.isEmpty(weight.getText()) ){
            weight.setError("Enter value");
            weight.requestFocus();
        }
        if(TextUtils.isEmpty(height.getText())){
            height.setError("Enter value");
            height.requestFocus();
        }
        else {
            float weightValue = Float.parseFloat(S1);
            float heightValue = Float.parseFloat(S2) / 100;

            float mult = multiply(heightValue, heightValue);
            float div = divide(weightValue, mult);
            float bmi = div;

            if (bmi < 16) {
                BMIresult = "Severly under weight";
            } else if (bmi < 18.5) {
                BMIresult = "Under weight";
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                BMIresult = "Normal weight";
            } else if (bmi >= 25 && bmi <= 29.9) {
                BMIresult = "Overweight";
            } else {
                BMIresult = "Obese";
            }

            calculation = "Result:\n" + bmi + "\n" + BMIresult;
            resulttext.setText(calculation);
        }

    }

    protected float multiply(float x,float y){
        return  x * y;
    }

    protected float divide(float a,float z){
        return a / z;
    }
}