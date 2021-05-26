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
import com.example.healthapa.entities.Parcours;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ListeParcours extends DialogFragment{

    FragmentManager fragmentManager;
    FloatingActionButton floatingActivityButton;




    private RecyclerView mRecyclerView;
    private MonRecyclerViewAdapterParcours mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View view;

    CreateParcoursFragment addParcoursFragment = new CreateParcoursFragment();

    public ListeParcours(){


    }

    public static ListeParcours newInstance(FragmentManager fragmentManager){
        ListeParcours listeParcours = new ListeParcours();
        listeParcours.fragmentManager = fragmentManager;
        return listeParcours;
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
        view = inflater.inflate(R.layout.liste_parcours, container, false);

        // Add the following lines to create RecyclerView
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(false);

        //Exercice 1 on remplace la ligne crÃ©ant le LayoutManager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //mLayoutManager=new LinearLayoutManager(this,GridLayoutManager.VERTICAL, false);

        //mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MonRecyclerViewAdapterParcours(getContext(), getDataSource());
        mRecyclerView.setAdapter(mAdapter);


        floatingActivityButton = view.findViewById(R.id.floatingActionButton);
        floatingActivityButton.setOnClickListener(v -> {
            addParcoursFragment = CreateParcoursFragment.newInstance(fragmentManager);
            addParcoursFragment.show(getActivity().getSupportFragmentManager(), "addParcoursFragment");



        });

        return view;
    }

    private ArrayList<LinkedHashMap<String, String>> getDataSource(){

        ArrayList<LinkedHashMap<String, String>> parcours = new ArrayList<>();

        int cnt = 0;
        for (Parcours pc: addParcoursFragment.getListParcours())
        {
            LinkedHashMap<String, String> parcours1 = new LinkedHashMap<>();

            cnt++;
            String count = String.valueOf(cnt);

            Log.d("succes","---> Parcours n°"+cnt);
            Log.d("succes","Nom "+pc.getTitre());
            Log.d("succes","Catégorie "+pc.getCategory());
            Log.d("succes","Description "+pc.getDescription());

            parcours1.put("nom", pc.getTitre());
            parcours1.put("categorie", pc.getCategory());
            parcours1.put("description", pc.getDescription());
            parcours1.put("activite", "SOON...");
            parcours.add(parcours1);
        }

        return parcours;

    }

}