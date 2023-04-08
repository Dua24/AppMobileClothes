package com.example.appmobileclothes.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmobileclothes.Models.Order;
import com.example.appmobileclothes.Repositories.FirebaseRepository;

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

    public void addOrder(Order data, String key) {
        firebaseRepository.addFirebaseData("Invoice", data, key);
    }

    public String getOrderKey() {
        return firebaseRepository.getKey("Invoice");
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

    public static Order getOrderById(ArrayList<Order> orders, String id) {
        for (Order order : orders) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }
}
