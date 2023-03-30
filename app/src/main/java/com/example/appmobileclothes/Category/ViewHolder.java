package com.example.appmobileclothes.Category;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobileclothes.R;

import java.io.Serializable;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private CategoryAdapter categoryAdapter;
    private TextView tv_CategoryName;
    private ImageView iv_Image;
    private Context mainActivity;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public TextView getTv_CategoryName() {
        return tv_CategoryName;
    }

    public void setTv_CategoryName(TextView tv_CategoryName) {
        this.tv_CategoryName = tv_CategoryName;
    }

    public ImageView getIv_Image() {
        return iv_Image;
    }

    public void setIv_Image(ImageView iv_Image) {
        this.iv_Image = iv_Image;
    }

    public ViewHolder(View itemView, CategoryAdapter categoryAdapter, Context mainActivity) {
        super(itemView);

        tv_CategoryName = itemView.findViewById(R.id.tv_CategoryName);
        iv_Image = itemView.findViewById(R.id.iv_Image);

        this.mainActivity = mainActivity;
        this.categoryAdapter = categoryAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}