package com.example.appmobileclothes.Controllers;

import com.example.appmobileclothes.FirebaseDb;
import com.example.appmobileclothes.Product.Product;

import java.util.ArrayList;

public class ProductDAL {
    public static ArrayList<Product> products;

    public ProductDAL() {
        products = FirebaseDb.fetchData();
    }

    public ArrayList<Product> getAll() {
        return products;
    }

    public static Product getById(int id) {
        return  products.get(id);
    }
}
