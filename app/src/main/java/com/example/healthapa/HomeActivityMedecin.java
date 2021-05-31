package com.example.healthapa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class HomeActivityMedecin extends DialogFragment {

    FragmentManager fragmentManager;

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
        return view;
    }
}