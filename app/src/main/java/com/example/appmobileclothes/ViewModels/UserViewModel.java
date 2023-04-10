package com.example.appmobileclothes.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmobileclothes.Models.User;
import com.example.appmobileclothes.Repositories.FirebaseRepository;

import java.util.ArrayList;

public class UserViewModel extends ViewModel {
    private FirebaseRepository firebaseRepository;
    private LiveData<ArrayList<User>> usersLiveData;

    public UserViewModel() {
        firebaseRepository = new FirebaseRepository();
        usersLiveData = firebaseRepository.getFirebaseData("Users", User.class);
    }

    public LiveData<ArrayList<User>> getUsersLiveData() {
        return usersLiveData;
    }

    public LiveData<User> getUserByIdFromDb(String userId) {
        return firebaseRepository.getFirebaseSingleData("Users/" + userId, User.class);
    }
}