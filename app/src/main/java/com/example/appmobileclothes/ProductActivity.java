package com.example.appmobileclothes;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt_add2cart, bt_sub, bt_add;
    TextView tv_quantity, tv_name, tv_price, tv_total;
    ImageView iv_image;
    int quantity = 1, max, cost, total_price;
    String total, name, image;
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


        Object[] objArr = (Object[]) getIntent().getSerializableExtra("data");
        name = (String) objArr[0];
        cost = (int) objArr[1];
        image = (String) objArr[2];
        max = (int) objArr[3];

        tv_name.setText(name);
        tv_price.setText(Integer.toString(cost));
        tv_total.setText(Integer.toString(cost));

        // Get a reference to the Firebase Storage instance
        FirebaseStorage storage = FirebaseStorage.getInstance();

// Create a storage reference to the image file
        StorageReference imageRef = storage.getReference().child("Products/"+image);
        Picasso picasso = Picasso.get();
        picasso.setIndicatorsEnabled(true);
        picasso.load(String.valueOf(imageRef)).fit().centerCrop().into(iv_image);


        rollback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_sub: {
                if (quantity < 2) {
                    quantity = 1;
                    total_price = cost * quantity;
                    tv_total.setText(Integer.toString(total_price));
                    break;
                } else {
                    quantity -= 1;
                }
                total = Integer.toString(quantity);
                tv_quantity.setText(total);
                total_price = cost * quantity;
                tv_total.setText(Integer.toString(total_price));
                break;
            }
            case R.id.bt_add: {
                if (quantity < max) {
                    quantity += 1;
                    total = Integer.toString(quantity);
                    tv_quantity.setText(total);
                    total_price = cost * quantity;
                    tv_total.setText(Integer.toString(total_price));
                    break;
                } else {
                    quantity = max;
                    total = Integer.toString(quantity);
                    tv_quantity.setText(total);
                    total_price = cost * quantity;
                    tv_total.setText(Integer.toString(total_price));
                    break;
                }
            }
            case R.id.bt_add2cart: {
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
}