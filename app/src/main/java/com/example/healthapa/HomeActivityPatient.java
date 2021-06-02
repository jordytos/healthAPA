package com.example.healthapa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Timer;
import java.util.TimerTask;

public class HomeActivityPatient extends Fragment {

    FragmentManager fragmentManager;
    ImageView menuParcours, menuMedecin, menuSeance;

    ParcoursUser addParcoursFragment = new ParcoursUser();

    public HomeActivityPatient() {

    }

    public static HomeActivityPatient newInstance(FragmentManager fragmentManager) {
        HomeActivityPatient homeActivity = new HomeActivityPatient();
        homeActivity.fragmentManager = fragmentManager;

        return homeActivity;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.activity_home_patient, container, false);
        menuParcours = view.findViewById(R.id.imageViewParcoursPatient);
        menuMedecin = view.findViewById(R.id.imageViewMedecinPatient);
        menuSeance = view.findViewById(R.id.imageViewSeancePatient);

        menuParcours.setOnClickListener(v -> {
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ParcoursUser()).commit();
        });

        menuMedecin.setOnClickListener(v -> {
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListeProfessionnel()).commit();
        });

        menuSeance.setOnClickListener(v -> {
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListeSeance()).commit();
        });

        return view;
    }
}


