package com.example.appmobileclothes.Cart;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.appmobileclothes.R;
import com.example.appmobileclothes.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
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
            convertView = inflater.inflate(R.layout.cart_item, null);
            dataitem.iv_photo = convertView.findViewById(R.id.iv_photo);
            dataitem.tv_caption = convertView.findViewById(R.id.tv_title);
            convertView.setTag(dataitem);
        }
        else{
            dataitem = (MyView) convertView.getTag();
        }

        Storage.loadImageIntoImageView("banner-img", photo_list.get(position).getArticle_image(), dataitem.iv_photo, 300, 400);

//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageRef = storage.getReference("banner-img");
//        storageRef.child(photo_list.get(position).getArticle_image()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                Picasso.get().load(uri.toString()).resize(300,400).centerCrop().into(dataitem.iv_photo);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                notifyDataSetChanged();
//            }
//        });

        dataitem.tv_caption.setText(photo_list.get(position).getArticle_title());
        return convertView;
    }

    private static class MyView {
        ImageView iv_photo;
        TextView tv_caption;
    }
}
