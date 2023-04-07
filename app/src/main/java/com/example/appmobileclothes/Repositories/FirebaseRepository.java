package com.example.appmobileclothes.Repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseRepository {
    private DatabaseReference dbRef;

    public FirebaseRepository() {
        dbRef = FirebaseDatabase.getInstance().getReference();
    }

    public <T> LiveData<ArrayList<T>> getFirebaseData(String nodePath, Class<T> dataType) {
        MutableLiveData<ArrayList<T>> firebaseData = new MutableLiveData<>();
        dbRef.child(nodePath).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<T> dataList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    T data = dataSnapshot.getValue(dataType);
                    dataList.add(data);
                }
                firebaseData.setValue(dataList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                firebaseData.setValue(null);
            }
        });
        return firebaseData;
    }

    public <T> void deleteFirebaseData(String nodePath, String childPath) {
        dbRef.child(nodePath).child(childPath).removeValue();
    }

    public <T> void updateFirebaseData(String nodePath, String childPath, String detailPath, Object newDetail) {
        dbRef.child(nodePath).child(childPath).child(detailPath).setValue(newDetail);
    }
}

