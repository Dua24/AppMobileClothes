package com.example.appmobileclothes.Product;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    private ArrayList<Product> product_list;
    private Context context;
    @Override
    public int getCount() {
        return product_list.size();
    }

    @Override
    public Object getItem(int position) {
        return product_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return product_list.get(position).getProduct_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
