package com.example.appmobileclothes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import com.example.appmobileclothes.UI.Adapters.ProductAdapter;
import com.example.appmobileclothes.ViewModels.ProductViewModel;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    private SearchView searchView;
    private Button btn_male, btn_female;
    int pressedBtnId = 0;
    private String gender;
    private int categoryId;
    private ProductViewModel productViewModel;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btn_male = findViewById(R.id.btn_male);
        btn_male.setOnClickListener(this);
        btn_female = findViewById(R.id.btn_female);
        btn_female.setOnClickListener(this);

        pressButton(btn_male);

        searchView = findViewById(R.id.searchView_search);
        searchView.clearFocus();

        Intent intent = getIntent();

        // Retrieve the data from the Intent
        String productName = intent.getStringExtra("productName");
        categoryId = intent.getIntExtra("categoryId", 99);

        //GridView for products
        GridView mGridView = findViewById(R.id.gridview_product_search);
        productAdapter = new ProductAdapter(getBaseContext());
        mGridView.setAdapter(productAdapter);

        //Retrieve products data
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String productName) {
                productViewModel.searchProducts(productName, categoryId, gender).observe(SearchActivity.this, products -> {
                    if (products != null) {
                        productAdapter.setProducts(products);
                    }
                });

                return true;
            }

            @Override
            public boolean onQueryTextChange(String productName) {
                productViewModel.searchProducts(productName, categoryId, gender).observe(SearchActivity.this, products -> {
                    if (products != null) {
                        productAdapter.setProducts(products);
                    }
                });

                return true;
            }
        });

        searchView.setQuery(productName, false);
    }

    private void changeBtnColor(@NonNull Button btn) {
        btn.setBackgroundColor(getColor(R.color.secondary_color));
        btn.setTextColor(getColor(R.color.white));
        pressedBtnId = btn.getId();
    }

    private void disposeBtnColor(@NonNull Button btn) {
        btn.setBackgroundColor(getColor(R.color.white));
        btn.setTextColor(getColor(R.color.black));
        pressedBtnId = 0;
    }

    private void pressButton(Button btn) {
        String btnText = btn.getText().toString();
        gender = btnText;

        //If pressed button's id is already existed -> dispose it
        if (pressedBtnId != 0) {
            disposeBtnColor(findViewById(pressedBtnId));
        }

        changeBtnColor(btn);
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        pressButton(btn);

        // Manually trigger search even if search query is empty
        String query = searchView.getQuery().toString();
        if (query.isEmpty()) {
            onQueryTextSubmit("");
        } else {
            searchView.setQuery(query, true);
        }
    }

    private void onQueryTextSubmit(String query) {
        productViewModel.searchProducts(query, categoryId, gender).observe(SearchActivity.this, products -> {
            if (products != null) {
                productAdapter.setProducts(products);
            }
        });
    }
}