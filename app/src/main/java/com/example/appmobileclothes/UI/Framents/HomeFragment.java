package com.example.appmobileclothes.UI.Framents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.appmobileclothes.UI.Adapters.CategoryAdapter;
import com.example.appmobileclothes.R;
import com.example.appmobileclothes.UI.Adapters.BannerAdapter;
import com.example.appmobileclothes.ViewModels.BannerViewModel;
import com.example.appmobileclothes.ViewModels.CategoryViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    private BannerViewModel bannerViewModel;
    private CategoryViewModel categoryViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_home, container, false);

        //RecyclerView for Categories
        RecyclerView mRecyclerView = contentView.findViewById(R.id.recyclerview);
        CategoryAdapter categoryAdapter = new CategoryAdapter(contentView.getContext());
        mRecyclerView.setAdapter(categoryAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(contentView.getContext(), LinearLayoutManager.HORIZONTAL, false));

        //Retrieve data for ViewPager2
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        categoryViewModel.getCategoriesLiveData().observe(getViewLifecycleOwner(), categories -> {
            if (categories != null){
                categoryAdapter.setCategories(categories);
            }
        });

        //ViewPager2 for Banners
        ViewPager2 mViewPager2 = contentView.findViewById(R.id.viewPager);
        BannerAdapter bannerAdapter = new BannerAdapter(contentView.getContext(), mViewPager2);
        mViewPager2.setAdapter(bannerAdapter);

        //Retrieve data for ViewPager2
        bannerViewModel = new ViewModelProvider(this).get(BannerViewModel.class);
        bannerViewModel.getBannersLiveData().observe(getViewLifecycleOwner(), banners -> {
            if (banners != null) {
                bannerAdapter.setBanners(banners);
            }
        });

        //Set Transformer for ViewPager2
        mViewPager2.setClipToPadding(false);
        mViewPager2.setClipChildren(false);
        mViewPager2.setOffscreenPageLimit(3);
        mViewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        mViewPager2.setPageTransformer(compositePageTransformer);

        return contentView;
    }
}