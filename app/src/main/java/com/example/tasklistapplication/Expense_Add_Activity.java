package com.example.tasklistapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Expense_Add_Activity extends AppCompatActivity {

    private ArrayList<String> amountList;
    private ArrayList<String> descList;
    private String total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_add);
        Intent i = getIntent();
        total = i.getStringExtra("total");
        amountList = i.getStringArrayListExtra("amountList");
        descList = i.getStringArrayListExtra("descList");
    }

    @SuppressLint("SetTextI18n")
    public void addAmount(View v){

        String amountVal = ((EditText)findViewById(R.id.amountInput)).getText().toString();
        String descVal = ((EditText)findViewById(R.id.descInput)).getText().toString();
        Intent i = new Intent(this, Expense_Tracker.class);

        if(amountVal.equals("")) {
            ((TextView) findViewById(R.id.message)).setText("Need to Add A Value");
            return;
        }

        if(descVal.equals("")){
            ((TextView) findViewById(R.id.message)).setText("Need to Add A Description");
            return;
        }

        amountVal = "$" + amountVal;
        i.putExtra("amountVal", amountVal);
        i.putExtra("descVal", descVal);
        i.putStringArrayListExtra("amountList", amountList);
        i.putStringArrayListExtra("descList", descList);
        i.putExtra("total",total);
        startActivity(i);

    }

    public void cancel(View v){
        Intent i = new Intent(this, Expense_Tracker.class);
        i.putStringArrayListExtra("amountList", amountList);
        i.putStringArrayListExtra("descList", descList);
        i.putExtra("total", total);
        i.putExtra("amountVal", "cancel");
        startActivity(i);
    }

}