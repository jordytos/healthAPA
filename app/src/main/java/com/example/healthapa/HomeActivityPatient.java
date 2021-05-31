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
    ImageView menuParcours;

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

        menuParcours.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(),login.class);
            startActivity(intent);

            Log.d("success","Butoon clické");
            FragmentTransaction fr = getFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container,new ParcoursUser());
            fr.commit();

        });

        return view;
    }
}


