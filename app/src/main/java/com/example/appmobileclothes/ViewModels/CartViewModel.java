package com.example.appmobileclothes.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmobileclothes.Models.Cart;
import com.example.appmobileclothes.Models.Product;
import com.example.appmobileclothes.Repositories.FirebaseRepository;

import java.util.ArrayList;

public class CartViewModel extends ViewModel {
    private static FirebaseRepository firebaseRepository;
    private LiveData<ArrayList<Cart>> cartsLiveData;

    public CartViewModel() {
        firebaseRepository = new FirebaseRepository();
        cartsLiveData = firebaseRepository.getFirebaseData("Carts", Cart.class);
    }

    public LiveData<ArrayList<Cart>> getCartsLiveData() {
        return cartsLiveData;
    }

    public static ArrayList<Cart> getCartsByUserId(ArrayList<Cart> cartList, String userId) {
        ArrayList<Cart> cartArrayList = new ArrayList<>();
        for (Cart cart : cartList) {
            if (cart.getUser_id().equals(userId)) {
                cartArrayList.add(cart);
            }
        }
        return cartArrayList;
    }

    public static void deleteCart(String id){
        firebaseRepository.deleteFirebaseData("Carts", id);
    }
}
