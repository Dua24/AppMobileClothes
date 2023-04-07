package com.example.appmobileclothes.UI.Framents;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobileclothes.Models.Cart;
import com.example.appmobileclothes.R;
import com.example.appmobileclothes.UI.Adapters.CartAdapter;
import com.example.appmobileclothes.ViewModels.CartViewModel;
import com.example.appmobileclothes.ViewModels.ProductViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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

    CartViewModel cartViewModel;
    ProductViewModel productViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_cart, container, false);

        ImageView iv_empty = contentView.findViewById(R.id.iv_empty_cart);


        String id = "";
        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
        }

        //RecyclerView for Carts
        RecyclerView recyclerView = contentView.findViewById(R.id.cartFragment);
        CartAdapter cartAdapter = new CartAdapter(contentView.getContext(), id);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(contentView.getContext()));

        ArrayList<Cart> cartArrayList = new ArrayList<>();

        //Retrieve carts data
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        String finalId = id;
        cartViewModel.getCartsLiveData().observe(getViewLifecycleOwner(), carts -> {
            if (carts != null) {
                ArrayList<Cart> list = CartViewModel.getCartsByUserId(carts, finalId);
                cartAdapter.setCarts(list);
                if (list.size() > 0){
                    iv_empty.setVisibility(View.GONE);
                }
            }
        });

        //Retrieve products data
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getProductsLiveData().observe(getViewLifecycleOwner(), products -> {
            if (products != null) {
                cartAdapter.setProducts(products);
            }
        });

        return contentView;
    }
}