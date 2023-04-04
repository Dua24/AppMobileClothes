package com.example.appmobileclothes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.appmobileclothes.Cart.CartFragment;
import com.example.appmobileclothes.Order.OrderFragment;
import com.example.appmobileclothes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());


        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.cart:
                    replaceFragment(new CartFragment());
                    break;
                case R.id.profile:
                    ProfileFragment fragment_profile = new ProfileFragment();
                    Bundle args = new Bundle();
                    args.putString("id", getIntent().getStringExtra("id"));
                    args.putString("username", getIntent().getStringExtra("username"));
                    args.putString("email", getIntent().getStringExtra("email"));
                    fragment_profile.setArguments(args);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, fragment_profile)
                            .commit();
                    break;
                case R.id.order:
                    replaceFragment(new OrderFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}