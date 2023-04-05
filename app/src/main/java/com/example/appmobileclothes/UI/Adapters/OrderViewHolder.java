package com.example.appmobileclothes.UI.Adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobileclothes.R;
import com.example.appmobileclothes.UI.Adapters.OrderAdapter;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private OrderAdapter orderAdapter;
    private Context mainActivity;
    private TextView tv_title, tv_date, tv_price;
    private String id;

    public OrderViewHolder(@NonNull View itemView, OrderAdapter orderAdapter, Context mainActivity, TextView tv_title, TextView tv_date, TextView tv_price, ImageView iv_image, String id) {
        super(itemView);
        this.orderAdapter = orderAdapter;
        this.mainActivity = mainActivity;
        this.tv_title = tv_title;
        this.tv_date = tv_date;
        this.tv_price = tv_price;
        this.id = id;
    }


    public OrderViewHolder(@NonNull View itemView, OrderAdapter orderAdapter, Context context) {
        super(itemView);

        tv_title = itemView.findViewById(R.id.tv_title);
        tv_price = itemView.findViewById(R.id.tv_price);
        tv_date = itemView.findViewById(R.id.tv_date);

        this.mainActivity = context;
        this.orderAdapter = orderAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
