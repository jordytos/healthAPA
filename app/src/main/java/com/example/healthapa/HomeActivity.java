package com.example.healthapa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class HomeActivity extends DialogFragment {

    FragmentManager fragmentManager;

    public HomeActivity() {

    }

    public static HomeActivity newInstance(FragmentManager fragmentManager) {
        HomeActivity homeActivity = new HomeActivity();
        homeActivity.fragmentManager = fragmentManager;
        return homeActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_home, container, false);
        return view;
    }
}