package com.example.appmobileclothes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    Button registerBtn;
    TextView email,username,password,confirmPass,signIn;
    private DatabaseReference userRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerBtn = findViewById(R.id.RegisterBtn);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmPass = findViewById(R.id.confirmPassword);
        signIn = findViewById(R.id.signinText);
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validate validator = new Validate();
                if(email.getText().toString().length()>0 && validator.isValidEmail(email.getText().toString())){
                    if(validator.isValidInfo(username.getText().toString()) && validator.isValidInfo(password.getText().toString())){
                        if(confirmPass.getText().toString().length()>0&&validator.isValidConfirmPassword(confirmPass.getText().toString(),password.getText().toString())){
                            String key=userRef.push().getKey();
                            Users us = new Users(key,username.getText().toString(),email.getText().toString(),password.getText().toString());

                            userRef.child(key).setValue(us).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(Register.this,"Sign up successfully",Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Register.this,"Sign up failed",Toast.LENGTH_LONG).show();
                                }
                            });
                            Intent intent = new Intent(getBaseContext(), Login.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Register.this,"Incorrect confirm password",Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        Toast.makeText(Register.this,"Invalid username or password (min_length=6)",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Register.this,"Invalid Email",Toast.LENGTH_SHORT).show();

                }

            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Login.class);
                startActivity(intent);
            }
        });
    }
}