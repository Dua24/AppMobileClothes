package com.example.appmobileclothes;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView id,name;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        id = itemView.findViewById(R.id.id);
        name = itemView.findViewById(R.id.name);
    }
}
