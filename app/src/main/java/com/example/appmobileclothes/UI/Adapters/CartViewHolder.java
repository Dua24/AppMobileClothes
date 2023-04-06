package com.example.appmobileclothes.UI.Adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobileclothes.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private CartAdapter cartAdapter;
    private Context mainActivity;
    private TextView tv_title, tv_price, tv_quantity;
    private ImageView iv_image;
    private String id;

    public TextView getTv_title() {
        return tv_title;
    }

    public void setTv_title(TextView tv_title) {
        this.tv_title = tv_title;
    }

    public TextView getTv_price() {
        return tv_price;
    }

    public void setTv_price(TextView tv_price) {
        this.tv_price = tv_price;
    }

    public TextView getTv_quantity() {
        return tv_quantity;
    }

    public void setTv_quantity(TextView tv_quantity) {
        this.tv_quantity = tv_quantity;
    }

    public ImageView getIv_image() {
        return iv_image;
    }

    public void setIv_image(ImageView iv_image) {
        this.iv_image = iv_image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CartViewHolder(@NonNull View itemView, CartAdapter cartAdapter, Context context) {
        super(itemView);

        tv_title = itemView.findViewById(R.id.tv_title);
        tv_price = itemView.findViewById(R.id.tv_price);
        tv_quantity = itemView.findViewById(R.id.tv_quantity);
        iv_image = itemView.findViewById(R.id.iv_cart_item);

        this.mainActivity = context;
        this.cartAdapter = cartAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
