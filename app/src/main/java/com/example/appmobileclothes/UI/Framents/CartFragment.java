package com.example.appmobileclothes.UI.Framents;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobileclothes.LoginActivity;
import com.example.appmobileclothes.Models.Cart;
import com.example.appmobileclothes.Models.Order;
import com.example.appmobileclothes.Models.OrderDetail;
import com.example.appmobileclothes.Models.Product;
import com.example.appmobileclothes.R;
import com.example.appmobileclothes.UI.Adapters.CartAdapter;
import com.example.appmobileclothes.ViewModels.CartViewModel;
import com.example.appmobileclothes.ViewModels.OrderDetailViewModel;
import com.example.appmobileclothes.ViewModels.OrderViewModel;
import com.example.appmobileclothes.ViewModels.ProductViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
    Button btn_checkout;
    TextView tv_subtotal, tv_total, tv_shipping;
    String user_id = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_cart, container, false);

        ImageView iv_empty = contentView.findViewById(R.id.iv_empty_cart);
        btn_checkout = contentView.findViewById(R.id.btn_checkout);
        tv_subtotal = contentView.findViewById(R.id.tv_subtotal);
        tv_total = contentView.findViewById(R.id.tv_total);
        tv_shipping = contentView.findViewById(R.id.tv_shipping);


        Bundle args = getArguments();
        if (args != null) {
            user_id = args.getString("id");
        }

        //RecyclerView for Carts
        RecyclerView recyclerView = contentView.findViewById(R.id.cartFragment);
        CartAdapter cartAdapter = new CartAdapter(contentView.getContext());
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(contentView.getContext()));

        //Retrieve carts data
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        cartViewModel.getCartsLiveData().observe(getViewLifecycleOwner(), carts -> {
            if (carts != null) {
                ArrayList<Cart> list = CartViewModel.getCartsByUserId(carts, user_id);
                cartAdapter.setCarts(list);
                if (list.size() > 0) {
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

        cartAdapter.getSubtotal().observe(getViewLifecycleOwner(), total -> {
            if (total != null) {
                tv_subtotal.setText(String.valueOf(total));
                int ship = Integer.parseInt(tv_shipping.getText().toString());
                int totals = total + ship;
                tv_total.setText(String.valueOf(totals));
            }
        });

        btn_checkout.setOnClickListener(checkout);

        return contentView;
    }

    public View.OnClickListener checkout = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //add Order data into firebase
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String strDate = formatter.format(now);
            String total = tv_total.getText().toString();

            OrderViewModel orderViewModel = new OrderViewModel();
            String key = orderViewModel.getOrderKey();
            Order order = new Order(key, strDate, "Invoice", user_id, total);

            orderViewModel.addOrder(order, key);

            //add Order Detail into firebase
            cartViewModel = new ViewModelProvider(getActivity()).get(CartViewModel.class);
            productViewModel = new ViewModelProvider(getActivity()).get(ProductViewModel.class);
            cartViewModel.getCartsLiveData().observe(getViewLifecycleOwner(), carts -> {
                if (carts == null) {
                    return;
                }
                ArrayList<Cart> list = CartViewModel.getCartsByUserId(carts, user_id);
                productViewModel.getProductsLiveData().observe(getViewLifecycleOwner(), products -> {
                    if (products == null) {
                        return;
                    }
                    for (Cart cart : list) {
                        OrderDetailViewModel orderDetailViewModel = new OrderDetailViewModel();
                        String detailKey = orderDetailViewModel.getOrderDetailKey();

                        Product product = ProductViewModel.getProductByIdFromList(products, cart.getProd_id());


                        OrderDetail orderDetail = new OrderDetail(detailKey, key, cart.getProd_id(), product.getName(),
                                cart.getQuantity(), product.getPrice());
                        orderDetailViewModel.addOrderDetail(orderDetail, detailKey);

                        //Delete Cart
//                        CartViewModel.deleteCart(cart.getId());
                    }

                });

            });
        }
    };

    /**
     * A simple {@link Fragment} subclass.
     * Use the {@link ProfileFragment#newInstance} factory method to
     * create an instance of this fragment.
     */
    public static class ProfileFragment extends Fragment {


        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;
        TextView username,email;
        Button logoutBtn,saveInfo;
        EditText editUsername,editEmail;
        public ProfileFragment() {
            // Required empty public constructor
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        public static ProfileFragment newInstance(String param1, String param2) {
            ProfileFragment fragment = new ProfileFragment();
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

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_profile, container, false);
            DatabaseReference userRef= FirebaseDatabase.getInstance().getReference("Users");
            username = view.findViewById(R.id.textView14);
            email = view.findViewById(R.id.textView15);
            editEmail = view.findViewById(R.id.editTextTextPersonName2);
            editUsername = view.findViewById(R.id.editTextTextPersonName4);
            logoutBtn = view.findViewById(R.id.button6);
            saveInfo = view.findViewById(R.id.save);

            SharedPreferences mPreferences= getActivity().getSharedPreferences("isLoggin", Context.MODE_PRIVATE);

            String id;
            Bundle args = getArguments();
            if (args != null) {
                id = args.getString("id");
                String nameUser = args.getString("username");
                String emailUser = args.getString("email");
                username.setText(nameUser);
                email.setText(emailUser);
                editEmail.setText(emailUser);
                editUsername.setText(nameUser);
                logoutBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), LoginActivity.class);

                        // clear preferences
                        SharedPreferences.Editor editor = mPreferences.edit();
                        editor.clear();
                        editor.apply();
                        startActivity(intent);
                    }
                });
                saveInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userRef.child(id).child("name").setValue(editUsername.getText().toString());
                        Toast.makeText(getContext(),"Update info successfully",Toast.LENGTH_LONG).show();
                    }
                });
            }


            // Inflate the layout for this fragment
            return view;
        }
    }
}