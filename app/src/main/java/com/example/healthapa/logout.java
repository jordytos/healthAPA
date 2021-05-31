package com.example.healthapa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class logout extends Fragment {
    private FirebaseAuth mAuth;
    FragmentManager fragmentManager;
    Button logoutBtn;
    View view;

    public logout(){

    }

    public static logout newInstance(FragmentManager fragmentManager){
        logout deco = new logout();
        deco.fragmentManager = fragmentManager;
        return deco;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_logout, container, false);

        logoutBtn = view.findViewById(R.id.logoutBtn);
        mAuth = FirebaseAuth.getInstance();


        logoutBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity().getApplicationContext(), login.class);
                startActivity(intent);
            }
        });

        return view;
    }
}