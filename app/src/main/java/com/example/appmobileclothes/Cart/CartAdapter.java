package com.example.appmobileclothes.Cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobileclothes.Controllers.ProductDAL;
import com.example.appmobileclothes.Product.Product;
import com.example.appmobileclothes.R;
import com.example.appmobileclothes.Storage;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private ArrayList<Cart> cart_list;
    private Context context;
    private final LayoutInflater mInfalter;
    private String user_id;

    public CartAdapter(ArrayList<Cart> cart_list, Context context, String id) {
        this.cart_list = cart_list;
        this.context = context;
        this.user_id = id;
        mInfalter = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View mItemView = mInfalter.inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(mItemView, this, context);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        // Get element from your dataset at this position and replace the contents of the view with that element
        Cart mCurrent = cart_list.get(position);
        if (mCurrent.getUser_id().equals(user_id)) {
            Product product = ProductDAL.getById(mCurrent.getProd_id());

            holder.setId(mCurrent.getId());
            holder.getTv_title().setText(product.getName());
            holder.getTv_price().setText(product.getPrice());
            holder.getTv_quantity().setText(product.getQuantity());

            Storage.loadImageIntoImageView("product-img", product.getImage(), holder.getIv_image());
        }
    }

    @Override
    public int getItemCount() {
        return cart_list.size();
    }
}
