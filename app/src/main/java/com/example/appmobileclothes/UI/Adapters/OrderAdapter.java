package com.example.appmobileclothes.UI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobileclothes.Models.Order;
import com.example.appmobileclothes.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    private ArrayList<Order> order_list;
    private Context context;
    private final LayoutInflater mInfalter;

    FirebaseDatabase database;
    DatabaseReference dbRef;

    public OrderAdapter(ArrayList<Order> order_list, Context context) {
        this.order_list = order_list;
        this.context = context;
        mInfalter = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View mItemView = mInfalter.inflate(R.layout.order_item, parent, false);
        return new OrderViewHolder(mItemView, this, context);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        // Get element from your dataset at this position and replace the contents of the view with that element
        Order mCurrent = order_list.get(position);
//        DatabaseReference Ref = FirebaseDatabase.getInstance().getReference("Carts");
    }

    @Override
    public int getItemCount() {
        return order_list.size();
    }
}
