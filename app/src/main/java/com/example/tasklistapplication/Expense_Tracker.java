package com.example.tasklistapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;


public class Expense_Tracker extends AppCompatActivity {

    private ArrayList<String> amountList;
    private ArrayList<String> descList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_tracker);
        recyclerView = findViewById(R.id.recView);
        amountList = new ArrayList<>();
        descList = new ArrayList<>();
        Intent i = getIntent();
        String amountVal = i.getStringExtra("amountVal");
        String descVal = i.getStringExtra("descVal");
        if(amountVal == null)
            return;

        if(!amountVal.equals("cancel")) {
            amountList.add(amountVal);
            descList.add(descVal);
        }

        amountList.addAll(i.getStringArrayListExtra("amountList"));
        descList.addAll(i.getStringArrayListExtra("descList"));
        double intVal = Double.valueOf(amountVal.substring(1));
        double total = Double.valueOf(i.getStringExtra("total").substring(1));
        total += intVal;
        ((TextView)findViewById(R.id.runningTotal)).setText("$" + total);

        setAdapter();
    }

    private void setAdapter(){
        recyclerAdapter adapter = new recyclerAdapter(amountList, descList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void openAdd(View v){
        Intent i = new Intent(this, Expense_Add_Activity.class);
        i.putStringArrayListExtra("amountList", amountList);
        i.putStringArrayListExtra("descList", descList);
        String total = ((TextView)findViewById(R.id.runningTotal)).getText().toString();
        i.putExtra("total", total);
        startActivity(i);
    }

    public void goBack(View v) {
        Intent intent = new Intent(this, Home_Screen.class);
        intent.putStringArrayListExtra("descList", descList);
        intent.putStringArrayListExtra("amountList", amountList);
        startActivity(intent);
    }



}