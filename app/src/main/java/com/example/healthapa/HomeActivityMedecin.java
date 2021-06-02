package com.example.healthapa;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.healthapa.dao.UtilisateurDao;
import com.example.healthapa.dao.apaDatabase;
import com.example.healthapa.entities.Utilisateur;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class HomeActivityMedecin extends DialogFragment {

    FragmentManager fragmentManager;
    ImageView menuParcours, menuAdd,menuPatient;

    public HomeActivityMedecin() {

    }

    public static HomeActivityMedecin newInstance(FragmentManager fragmentManager) {
        HomeActivityMedecin homeActivity = new HomeActivityMedecin();
        homeActivity.fragmentManager = fragmentManager;
        return homeActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_home_medecin, container, false);

        menuParcours = view.findViewById(R.id.imageViewParcoursMedecin);
        menuPatient = view.findViewById(R.id.imageViewPatientMedecin);
        menuAdd = view.findViewById(R.id.imageViewAjoutMedecin);

        menuParcours.setOnClickListener(v -> {
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListeParcours()).commit();
        });

        menuPatient.setOnClickListener(v -> {
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListePatient()).commit();
        });

        menuAdd.setOnClickListener(v -> {
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListAddButton()).commit();
        });

        return view;
    }
}