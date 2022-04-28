package com.example.tasklistapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {

    private ArrayList<String> itemlist;

    public recyclerAdapter(ArrayList<String> itemlist){
        this.itemlist = itemlist;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView itemText;

        public MyViewHolder(final View view){
            super(view);
            itemText = view.findViewById(R.id.textView3);
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
        String item = itemlist.get(position);
        holder.itemText.setText(item);
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }
}
