package com.example.tasklistapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {

    private ArrayList<String> amountList;
    private ArrayList<String> descList;

    public recyclerAdapter(ArrayList<String> amountList, ArrayList<String> descList){
        this.amountList = amountList;
        this.descList = descList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView amountText;
        private TextView descText;

        public MyViewHolder(final View view){
            super(view);
            amountText = view.findViewById(R.id.amountView);
            descText = view.findViewById(R.id.descView);
        }
    }


    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_item_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String amount = amountList.get(position);
        String desc = descList.get(position);
        holder.amountText.setText(amount);
        holder.descText.setText(desc);

    }

    @Override
    public int getItemCount() {
        return amountList.size();
    }

}
