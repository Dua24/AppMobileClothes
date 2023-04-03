package com.example.appmobileclothes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
Button btnLogin;
TextView register,username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DatabaseReference userRef;

        register = findViewById(R.id.signupText);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.loginButton);
        userRef = FirebaseDatabase.getInstance().getReference("Users");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Users user = dataSnapshot.getValue(Users.class);
                            Log.d("usernameInput",username.getText().toString());
                            Log.d("passwordInput",password.getText().toString());
                            Log.d("username",user.getName());
                            Log.d("password",user.getPassword());
                            Log.d("compare",String.valueOf(username.getText().toString()==user.getName()));
                            if(username.getText().toString().equals(user.getName())  ){
                                if( password.getText().toString().equals(user.getPassword())){
                                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                    startActivity(intent);
                                    return;
                                }else{
                                    Toast.makeText(Login.this,"Sign in failed !!!!!",Toast.LENGTH_LONG).show();
                                }

                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Register.class);
                startActivity(intent);
            }
        });

    }
}