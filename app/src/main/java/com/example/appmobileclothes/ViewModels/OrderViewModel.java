package com.example.appmobileclothes.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmobileclothes.Models.Cart;
import com.example.appmobileclothes.Models.Order;
import com.example.appmobileclothes.Repositories.FirebaseRepository;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class OrderViewModel extends ViewModel {
    private static FirebaseRepository firebaseRepository;
    private LiveData<ArrayList<Order>> ordersLiveData;

    public OrderViewModel() {
        firebaseRepository = new FirebaseRepository();
        ordersLiveData = firebaseRepository.getFirebaseData("Invoice", Order.class);
    }

    public LiveData<ArrayList<Order>> getOrdersLiveData() {
        return ordersLiveData;
    }

    public void addOrder(Order data) {
        firebaseRepository.addFirebaseData("Invoice", data);
    }

    public static ArrayList<Order> getOrdersByUserId(ArrayList<Order> orders, String userId) {
        ArrayList<Order> orderArrayList = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUser_id().equals(userId)) {
                orderArrayList.add(order);
            }
        }
        return orderArrayList;
    }
}
