package com.example.appmobileclothes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
TextView tv1,tv2;
Button btn,btn2;
RecyclerView recyclerView;
private  DatabaseReference userRef;
private FirebaseRecyclerOptions<User> options;
private FirebaseRecyclerAdapter<User,MyViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.textview);
        tv2 = findViewById(R.id.textview2);
        btn = findViewById(R.id.button);
        btn2=findViewById(R.id.button2);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        userRef = FirebaseDatabase.getInstance("https://mobileappclothes-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Users");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User us = new User(13,"Duy Nguyen");
                String key=userRef.push().getKey();
                userRef.child(key).setValue(us).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(MainActivity.this,"Your data is Successfully added",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,"Sorry, you dont have permission",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRef.child("user1").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                           User us = snapshot.getValue(User.class);
                            tv1.setText(""+us.getId());
                            tv2.setText(us.getName());
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        options=new FirebaseRecyclerOptions.Builder<User>().setQuery(userRef,User.class).build();
        adapter = new FirebaseRecyclerAdapter<User, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull User model) {
                holder.id.setText(""+model.getId());
                holder.name.setText(model.getName());
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view,parent,false);
                return new MyViewHolder(v);
            }
        };
            adapter.startListening();
            recyclerView.setAdapter(adapter);

    }


}