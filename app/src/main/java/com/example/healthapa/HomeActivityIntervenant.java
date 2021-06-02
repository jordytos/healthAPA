package com.example.healthapa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class HomeActivityIntervenant extends Fragment {

    FragmentManager fragmentManager;
    ImageView menuParcours, menuSeance,menuPatient;

    public HomeActivityIntervenant() {

    }

    public static HomeActivityIntervenant newInstance(FragmentManager fragmentManager) {
        HomeActivityIntervenant homeActivity = new HomeActivityIntervenant();
        homeActivity.fragmentManager = fragmentManager;
        return homeActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_home_intervenant, container, false);

        menuParcours = view.findViewById(R.id.imageViewParcoursIntervenant);
        menuPatient = view.findViewById(R.id.imageViewPatientIntervenant);
        menuSeance = view.findViewById(R.id.imageViewSeanceIntervenant);

        menuParcours.setOnClickListener(v -> {
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListeParcours()).commit();
        });

        menuPatient.setOnClickListener(v -> {
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListePatient()).commit();
        });

        menuSeance.setOnClickListener(v -> {
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListAddButton()).commit();
        });

        return view;
    }
}