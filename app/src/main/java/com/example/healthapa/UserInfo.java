package com.example.healthapa;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.healthapa.entities.Utilisateur;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserInfo extends DialogFragment {

    TextView ageTxt, poidsTxt, emailTxt, phoneTxt, nomTxt, tailleTxt, roleTxt;
    private FirebaseAuth mAuth;



    FragmentManager fragmentManager;

    public UserInfo() {

    }

    public static UserInfo newInstance(FragmentManager fragmentManager) {
        UserInfo userInfo = new UserInfo();
        userInfo.fragmentManager = fragmentManager;
        return userInfo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_user_info, container, false);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String currentUserEmail = currentUser.getEmail();

        Log.d("jordy","User email"+currentUserEmail);




        nomTxt = view.findViewById(R.id.nomUser);
        ageTxt = view.findViewById(R.id.ageUser);
        tailleTxt = view.findViewById(R.id.tailleUser);
        poidsTxt = view.findViewById(R.id.poidsUser);
        emailTxt = view.findViewById(R.id.emailUser);
        roleTxt = view.findViewById(R.id.roleUser);
        phoneTxt = view.findViewById(R.id.phoneUser);

        /*String fullName = user.getNom_user() +" "+ user.getPrenom_user();
        nomTxt.setText(fullName);
        ageTxt.setText(user.getAge());
        tailleTxt.setText(user.getTaille());
        poidsTxt.setText(user.getPoids());
        emailTxt.setText(user.getEmail());
        roleTxt.setText(user.getRole());
        phoneTxt.setText(user.getTelephone());*/



        return view;
    }
}