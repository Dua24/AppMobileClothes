package com.example.appmobileclothes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HandleReferences extends AppCompatActivity {
    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_references);

        SharedPreferences mPreferences= getSharedPreferences("isLoggin",MODE_PRIVATE);
        if(mPreferences.getBoolean("isLogged",false)){
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Users user = dataSnapshot.getValue(Users.class);
                        if(mPreferences.getString("email","").equals(user.getEmail())){
                            if(mPreferences.getString("password","").equals(user.getPassword())){
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                intent.putExtra("id",user.getId());
                                intent.putExtra("username",user.getName());
                                intent.putExtra("email",user.getEmail());
                                startActivity(intent);
                                return;
                            }
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }else{
            Intent intent = new Intent(getBaseContext(), Login.class);
            startActivity(intent);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
}