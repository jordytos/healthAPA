package com.example.healthapa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class HomeActivityIntervenant extends Fragment {

    FragmentManager fragmentManager;

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
        return view;
    }
}