package com.example.appmobileclothes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobileclothes.Models.Order;
import com.example.appmobileclothes.Models.Product;
import com.example.appmobileclothes.Utilities.StorageUtils;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt_add2cart, bt_sub, bt_add;
    TextView tv_quantity, tv_name, tv_price, tv_total;
    ImageView iv_image;
    int quantity = 1, max, cost, total_price;
    String total;
    LinearLayout rollback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        iv_image = findViewById(R.id.iv_image);
        tv_quantity = findViewById(R.id.tv_quantity);
        tv_name = findViewById(R.id.tv_name);
        tv_price = findViewById(R.id.tv_price);
        tv_total = findViewById(R.id.tv_total);
        rollback = findViewById(R.id.rollback);

        assignId(bt_add2cart, R.id.bt_add2cart);
        assignId(bt_sub, R.id.bt_sub);
        assignId(bt_add, R.id.bt_add);


        Product product = (Product) getIntent().getSerializableExtra("data");

        cost = product.getPrice();
        max = product.getQuantity();

        tv_name.setText(product.getName());
        tv_price.setText(Integer.toString(cost));
        tv_total.setText(Integer.toString(cost));

        StorageUtils.loadStorageImageIntoImageView("product-img", product.getImg(), iv_image);

        rollback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_sub: {
                if (quantity < 2) {
                    quantity = 1;
                    tv_total.setText(totalPrice(quantity));
                    break;
                } else {
                    quantity -= 1;
                    tv_total.setText(totalPrice(quantity));
                }
                break;
            }
            case R.id.bt_add: {
                if (quantity < max) {
                    quantity += 1;
                    tv_total.setText(totalPrice(quantity));
                    break;
                } else {
                    quantity = max;
                    tv_total.setText(totalPrice(quantity));
                    break;
                }
            }
            case R.id.bt_add2cart: {
                Intent intent = new Intent(this, OrderDetailActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.rollback: {
                onBackPressed();
                break;
            }
        }
    }

    public void assignId(Button btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    public String totalPrice(int quantity) {
        total = Integer.toString(quantity);
        tv_quantity.setText(total);
        total_price = cost * quantity;
        return Integer.toString(total_price);
    }
}