package com.example.appmobileclothes;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView username,email;
    Button logoutBtn,saveInfo;
    EditText editUsername,editEmail;
    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        DatabaseReference userRef=FirebaseDatabase.getInstance().getReference("Users");
        username = view.findViewById(R.id.textView14);
        email = view.findViewById(R.id.textView15);
        editEmail = view.findViewById(R.id.editTextTextPersonName2);
        editUsername = view.findViewById(R.id.editTextTextPersonName4);
        logoutBtn = view.findViewById(R.id.button6);
        saveInfo = view.findViewById(R.id.save);
        String id;
        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
            String nameUser = args.getString("username");
            String emailUser = args.getString("email");
            username.setText(nameUser);
            email.setText(emailUser);
            editEmail.setText(emailUser);
            editUsername.setText(nameUser);
            logoutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), Login.class);
                    startActivity(intent);
                }
            });
            saveInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    userRef.child(id).child("name").setValue(editUsername.getText().toString());
                    Toast.makeText(getContext(),"Update info successfully",Toast.LENGTH_LONG).show();
                }
            });
        }


        // Inflate the layout for this fragment
        return view;
    }
}