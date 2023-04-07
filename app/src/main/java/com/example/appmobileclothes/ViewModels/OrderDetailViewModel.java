package com.example.appmobileclothes.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmobileclothes.Models.Order;
import com.example.appmobileclothes.Models.OrderDetail;
import com.example.appmobileclothes.Repositories.FirebaseRepository;

import java.util.ArrayList;

public class OrderDetailViewModel extends ViewModel {
    private static FirebaseRepository firebaseRepository;
    private LiveData<ArrayList<OrderDetail>> ordersLiveData;

    public OrderDetailViewModel() {
        firebaseRepository = new FirebaseRepository();
        ordersLiveData = firebaseRepository.getFirebaseData("Invoice_Detail", OrderDetail.class);
    }

    public LiveData<ArrayList<OrderDetail>> getOrdersLiveData() {
        return ordersLiveData;
    }

    public static void addOrder(OrderDetail data, String key){
//        firebaseRepository.addFirebsaeData(data, key);
    }
}
