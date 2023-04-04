package com.example.appmobileclothes.Cart;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobileclothes.Home.CategoryAdapter;
import com.example.appmobileclothes.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private CartAdapter cartAdapter;
    private Context mainActivity;
    private TextView tv_title, tv_price, tv_quantity;
    private ImageView iv_image;
    private String id;

    public CartViewHolder(@NonNull View itemView, CartAdapter cartAdapter, Context mainActivity, TextView tv_title,TextView tv_price,TextView tv_quantity, ImageView iv_image, String id) {
        super(itemView);
        this.cartAdapter = cartAdapter;
        this.mainActivity = mainActivity;
        this.tv_title = tv_title;
        this.tv_price = tv_price;
        this.tv_quantity = tv_quantity;
        this.iv_image = iv_image;
        this.id = id;
    }

    public CartAdapter getCartAdapter() {
        return cartAdapter;
    }

    public void setCartAdapter(CartAdapter cartAdapter) {
        this.cartAdapter = cartAdapter;
    }

    public Context getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(Context mainActivity) {
        this.mainActivity = mainActivity;
    }

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
        iv_image = itemView.findViewById(R.id.iv_Image);

        this.mainActivity = context;
        this.cartAdapter = cartAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
