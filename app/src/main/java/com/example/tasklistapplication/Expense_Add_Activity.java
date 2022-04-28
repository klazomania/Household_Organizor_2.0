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

    private ArrayList<String> itemList;
    private String total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_add);
        Intent i = getIntent();
        total = i.getStringExtra("total");
        itemList = i.getStringArrayListExtra("list");
    }

    @SuppressLint("SetTextI18n")
    public void addAmount(View v){

        String val = ((EditText)findViewById(R.id.amountInput)).getText().toString();
        Intent i = new Intent(this, Expense_Tracker.class);

        if(val.equals("")){
            ((TextView) findViewById(R.id.message)).setText("Need to Add A Value");
            return;
        }

        val = "$" + val;
        i.putExtra("val", val);
        i.putStringArrayListExtra("list", itemList);
        i.putExtra("total",total);
        startActivity(i);

    }

    public void cancel(View v){
        Intent i = new Intent(this, Expense_Tracker.class);
        i.putStringArrayListExtra("list", itemList);
        i.putExtra("total", total);
        i.putExtra("val", "cancel");
        startActivity(i);
    }
}