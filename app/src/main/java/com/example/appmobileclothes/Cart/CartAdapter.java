package com.example.appmobileclothes.Cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appmobileclothes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    private ArrayList<Cart> photo_list;
    private Context context;
    public CartAdapter(ArrayList<Cart> photo_list, Context context) {
        this.photo_list = photo_list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return photo_list.size();
    }

    @Override
    public Object getItem(int i) {
        return photo_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return photo_list.get(i).getArticle_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyView dataitem;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            dataitem = new MyView();
            convertView = inflater.inflate(R.layout.cart_product, null);
            dataitem.iv_photo = convertView.findViewById(R.id.iv_photo);
            dataitem.tv_caption = convertView.findViewById(R.id.tv_title);
            convertView.setTag(dataitem);
        }
        else{
            dataitem = (MyView) convertView.getTag();
        }
        Picasso.get().load(photo_list.get(position).getArticle_image()).resize(300,400).centerCrop().into(dataitem.iv_photo);
        dataitem.tv_caption.setText(photo_list.get(position).getArticle_title());
        return convertView;
    }

    private static class MyView {
        ImageView iv_photo;
        TextView tv_caption;
    }
}
