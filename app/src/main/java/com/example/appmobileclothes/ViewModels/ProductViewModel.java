package com.example.appmobileclothes.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmobileclothes.Models.Product;
import com.example.appmobileclothes.Repositories.FirebaseRepository;

import java.util.ArrayList;

public class ProductViewModel extends ViewModel {
    private FirebaseRepository firebaseRepository;
    private LiveData<ArrayList<Product>> productsLiveData;

    public ProductViewModel() {
        firebaseRepository = new FirebaseRepository();
        productsLiveData = firebaseRepository.getFirebaseData("Products", Product.class);
    }

    public LiveData<ArrayList<Product>> getProductsLiveData() {
        return productsLiveData;
    }

    public static Product getProductById(ArrayList<Product> productList, int productId) {
        for (Product product : productList) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }
}
