package com.example.healthapa;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthapa.entities.Activite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ListeActivite extends DialogFragment{

    FragmentManager fragmentManager;
    FloatingActionButton floatingActivityButton;




    private RecyclerView mRecyclerView;
    private MonRecyclerViewAdapterActivite mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View view;

    CreateActiviteFragment addActivityFragment = new CreateActiviteFragment();

    public ListeActivite(){


    }

    public static ListeActivite newInstance(FragmentManager fragmentManager){
        ListeActivite listeActivite = new ListeActivite();
        listeActivite.fragmentManager = fragmentManager;
        return listeActivite;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.liste_activite, container, false);

        // Add the following lines to create RecyclerView
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(false);

        //Exercice 1 on remplace la ligne crÃ©ant le LayoutManager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //mLayoutManager=new LinearLayoutManager(this,GridLayoutManager.VERTICAL, false);

        //mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MonRecyclerViewAdapterActivite(getContext(), getDataSource());
        mRecyclerView.setAdapter(mAdapter);


        floatingActivityButton = view.findViewById(R.id.floatingActionButton);
        floatingActivityButton.setOnClickListener(v -> {
            addActivityFragment = CreateActiviteFragment.newInstance(fragmentManager);
            addActivityFragment.show(getActivity().getSupportFragmentManager(), "addActivityFragment");



        });

        return view;
    }

    private ArrayList<LinkedHashMap<String, String>> getDataSource(){

        ArrayList<LinkedHashMap<String, String>> activites = new ArrayList<>();

        int cnt = 0;
        for (Activite ac: addActivityFragment.getListActivites())
        {
            LinkedHashMap<String, String> activite1 = new LinkedHashMap<>();

            cnt++;
            String count = String.valueOf(cnt);

            Log.d("succes","---> Activité n°"+cnt);
            Log.d("succes","Titre "+ac.getTitre());
            Log.d("succes","Descrip "+ac.getDescription());
            Log.d("succes","Duree "+ac.getDuree());

            activite1.put("titre", ac.getTitre());
            activite1.put("duree", ac.getDuree());
            activite1.put("description", ac.getDescription());
            activite1.put("structure", "SOON...");
            activites.add(activite1);
        }

        return activites;

    }

}