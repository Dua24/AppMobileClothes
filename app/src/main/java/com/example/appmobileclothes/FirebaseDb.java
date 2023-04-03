package com.example.appmobileclothes;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class FirebaseDb {
    public static <T> void loadDataIntoView(String reference, Class<T> dataClass, ArrayList<T> dataList, RecyclerView.Adapter<?> recyclerViewAdapter) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference(reference);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    T data = dataSnapshot.getValue(dataClass);
                    dataList.add(data);
                }
                if (recyclerViewAdapter != null) {
                    recyclerViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }

    public static <T> void loadDataIntoView(String reference, Class<T> dataClass, ArrayList<T> dataList, BaseAdapter baseAdapter) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference(reference);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    T data = dataSnapshot.getValue(dataClass);
                    dataList.add(data);
                }
                if (baseAdapter != null) {
                    baseAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }
}
