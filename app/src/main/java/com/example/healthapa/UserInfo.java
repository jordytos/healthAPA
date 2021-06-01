package com.example.healthapa;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthapa.dao.UtilisateurDao;
import com.example.healthapa.dao.apaDatabase;
import com.example.healthapa.entities.Utilisateur;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class UserInfo extends DialogFragment {

    TextView ageTxt, poidsTxt, emailTxt, phoneTxt, nomTxt, tailleTxt, roleTxt;
    private FirebaseAuth mAuth;

    private apaDatabase db;
    private UtilisateurDao utilisateurDao;

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

        new Thread(() -> {

            db = apaDatabase.getDatabase(getActivity().getApplicationContext());
            utilisateurDao = db.utilisateurDao();
            List<Utilisateur> user_list = utilisateurDao.findAllUser();


            getActivity().runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    Utilisateur new_user = new Utilisateur();

                    for (Utilisateur user: user_list
                    ) {
                        if(user.getEmail().equals(currentUserEmail)){
                            new_user = user;
                        }

                    }
                    String fullName = new_user.getPrenom_user() +" "+ new_user.getNom_user();
                    nomTxt.setText(fullName);
                    String ag = String.valueOf(new_user.getAge());
                    ageTxt.setText(ag);
                    String tl = String.valueOf(new_user.getTaille()) + "cm";
                    tailleTxt.setText(tl);
                    String p = String.valueOf(new_user.getPoids()) + "kg";
                    poidsTxt.setText(p);
                    emailTxt.setText(new_user.getEmail());
                    roleTxt.setText(new_user.getRole());
                    phoneTxt.setText(new_user.getTelephone());
                }
            });

        }).start();







        return view;
    }
}