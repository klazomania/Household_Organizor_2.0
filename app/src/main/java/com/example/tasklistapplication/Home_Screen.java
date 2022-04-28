package com.example.tasklistapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class Home_Screen extends AppCompatActivity {

    private ArrayList<String> amountList;
    private ArrayList<String> descList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Intent i = getIntent();
        amountList = i.getStringArrayListExtra("amountList");
        descList = i.getStringArrayListExtra("descList");
        String dateAndTime = java.text.DateFormat.getDateInstance().format(new Date());
        TextView currentDate = findViewById(R.id.textView);
        String message = "Today's date\n";
        String messageWithDate = message.concat(dateAndTime);
        currentDate.setText(messageWithDate);

    }

    public void openTaskList(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openExpenseTracker(View v) {
        Intent intent = new Intent(this, Expense_Tracker.class);
        intent.putStringArrayListExtra("amountList", amountList);
        intent.putStringArrayListExtra("descList", descList);
        startActivity(intent);
    }

    public void openEventList(View v) {
        Intent intent = new Intent(this, Events_List.class);
        startActivity(intent);
    }

    public void openChoreList(View v) {
        Intent intent = new Intent(this, Chore_List.class);
        startActivity(intent);
    }
}