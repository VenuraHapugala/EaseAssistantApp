package com.example.easeassistantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class AddExpenses extends AppCompatActivity {
    private EditText details,amount,type;
    private DatePicker datePicker;
    private Button save;
    private expDbHandler dbHandler;
    private Context context;

    AwesomeValidation validation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);

        details = findViewById(R.id.editexpDetails);
        amount = findViewById(R.id.editexpAmount);
        type = findViewById(R.id.expType);
        datePicker = findViewById(R.id.editexpDate);
        save = findViewById(R.id.expNew);
        context = this;

        dbHandler = new expDbHandler(context);

        //validations
        validation = new AwesomeValidation(ValidationStyle.BASIC);
        validation.addValidation(this,R.id.editexpDetails, RegexTemplate.NOT_EMPTY,R.string.invalid_expdetails);
        //validation.addValidation(this,R.id.expType,"(?=[Credit])"+"(?=[Debit])",R.string.invalid_extype);
        validation.addValidation(this,R.id.editexpAmount, RegexTemplate.NOT_EMPTY,R.string.invalid_examount);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check validations
                if(validation.validate()){
                    //success
                    Toast.makeText(context,"Successful Entry",Toast.LENGTH_SHORT).show();
                    String expdetails = details.getText().toString();
                    String expamount = amount.getText().toString();
                    String exxptype = type.getText().toString();
                    long expdate = datePicker.getMaxDate();

                    Expenses expenses = new Expenses(expdetails, expamount, exxptype, expdate);
                    dbHandler.addExpenses(expenses);
                    startActivity(new Intent(context, ExpHome.class));
                }else{
                    Toast.makeText(context,"Unsuccessful Entry",Toast.LENGTH_SHORT).show();
                }

            }

            public float balanceExp() {
                float total = 0;
                String ty = type.getText().toString();
                float am = Float.parseFloat(amount.getText().toString());

                if (ty == "Credit") {
                    total = total + am;
                }
                if (ty == "Debit") {
                    total = total - am;
                }
                return total;
            }
        });
    }


}