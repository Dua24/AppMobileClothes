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

public class Login extends AppCompatActivity {
Button btnLogin;
TextView register,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DatabaseReference userRef;

        register = findViewById(R.id.signinText);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.loginButton);
        userRef = FirebaseDatabase.getInstance().getReference("Users");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validate validator = new Validate();
                if (email.getText().toString().length()>0 && validator.isValidEmail(email.getText().toString())) {
                    if(validator.isValidInfo(password.getText().toString())){
                        userRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    Users user = dataSnapshot.getValue(Users.class);
                                    if(email.getText().toString().equals(user.getEmail())){
                                        if( password.getText().toString().equals(user.getPassword())){
                                            Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                            intent.putExtra("id",user.getId());
                                            intent.putExtra("username",user.getName());
                                            intent.putExtra("email",user.getEmail());
                                            startActivity(intent);
                                            return;
                                        }else{
                                            Toast.makeText(Login.this,"Incorrect password",Toast.LENGTH_LONG).show();
                                            break;
                                        }
                                    }

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }else{
                        Toast.makeText(Login.this,"Minimum password length of 6 characters required",Toast.LENGTH_LONG).show();

                    }

                } else {
                    Toast.makeText(Login.this,"Invalid email",Toast.LENGTH_LONG).show();
                }



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