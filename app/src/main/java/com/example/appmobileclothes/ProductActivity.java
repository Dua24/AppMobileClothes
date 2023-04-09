package com.example.appmobileclothes;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt_sub, bt_add, bt_add2cart;
    TextView tv_quantity, tv_description_detail, tv_product_name, tv_price_value;
    ImageView iv_main_pic;
    int quantity = 1;
    String total, name, price, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        iv_main_pic = findViewById(R.id.iv_main_pic);
        tv_quantity = findViewById(R.id.tv_quantity);
        tv_description_detail = findViewById(R.id.tv_description_detail);
        tv_product_name = findViewById(R.id.tv_product_name);
        tv_price_value = findViewById(R.id.tv_price_value);

        assignId(bt_sub, R.id.bt_sub);
        assignId(bt_add, R.id.bt_add);
        assignId(bt_add2cart, R.id.bt_add2cart);

        Object[] objArr = (Object[]) getIntent().getSerializableExtra("data");
        name = (String) objArr[0];
        int cost = (int) objArr[1];
        price = Integer.toString(cost);
        image = (String) objArr[2];
        tv_product_name.setText(name);
        tv_price_value.setText(price + "VND");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_sub: {
                if (quantity == 0) {
                    break;
                } else {
                    quantity -= 1;
                }
                total = Integer.toString(quantity);
                tv_quantity.setText(total);
                break;
            }
            case R.id.bt_add: {
                quantity += 1;
                total = Integer.toString(quantity);
                tv_quantity.setText(total);
                break;
            }
            case R.id.bt_add2cart: {
                break;
            }
        }
    }

    public void assignId(Button btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }
}