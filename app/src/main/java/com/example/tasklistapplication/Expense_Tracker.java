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

    static final String STATE_USER = "user";
    private String mUser;

    private ArrayList<String> itemList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            //itemList = savedInstanceState.getStringArrayList();
            mUser = savedInstanceState.getString(STATE_USER);
        } else {
            mUser = "NewUser";
        }


        setContentView(R.layout.activity_expense_tracker);
        recyclerView = findViewById(R.id.recView);
        itemList = new ArrayList<>();
        Intent i = getIntent();
        String val = i.getStringExtra("val");
        if(val == null)
            return;

        if(!val.equals("cancel"))
            itemList.add(val);

        itemList.addAll(i.getStringArrayListExtra("list"));
        double intVal = Double.valueOf(val.substring(1));
        double total = Double.valueOf(i.getStringExtra("total").substring(1));
        total += intVal;
        ((TextView)findViewById(R.id.runningTotal)).setText("$" + total);

        setAdapter();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(STATE_USER, mUser);
        savedInstanceState.putAll(savedInstanceState);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void setAdapter(){
        recyclerAdapter adapter = new recyclerAdapter(itemList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void openAdd(View v){
        Intent i = new Intent(this, Expense_Add_Activity.class);
        i.putStringArrayListExtra("list", itemList);
        String total = ((TextView)findViewById(R.id.runningTotal)).getText().toString();
        i.putExtra("total", total);
        startActivity(i);
    }

    public void goBack(View v) {
        Intent intent = new Intent(this, Home_Screen.class);
        startActivity(intent);
    }


}

