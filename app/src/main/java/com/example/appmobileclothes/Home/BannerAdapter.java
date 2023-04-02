package com.example.appmobileclothes.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.appmobileclothes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends RecyclerView.Adapter<BannerViewHolder> {
    private ArrayList<Banner> banners;
    private final LayoutInflater mInfalter;
    private ViewPager2 viewPager2;
    private Context context;

    public BannerAdapter(Context context, ArrayList<Banner> banners, ViewPager2 viewPager2) {
        mInfalter = LayoutInflater.from(context);
        this.banners = banners;
        this.context = context;
        this.viewPager2 = viewPager2;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BannerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View mItemView = mInfalter.inflate(R.layout.banner_item, viewGroup, false);
        return new BannerViewHolder(mItemView, this, context);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder viewHolder, int position) {
        // Get element from your dataset at this position and replace the contents of the view with that element
        Banner mCurrent = banners.get(position);
        viewHolder.setId(mCurrent.getId());
        Picasso.get().load(mCurrent.getImg_name()).into(viewHolder.getImageView());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return banners.size();
    }
}
