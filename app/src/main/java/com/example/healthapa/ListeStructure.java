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

import com.example.healthapa.dao.ParcoursDao;
import com.example.healthapa.dao.StructureDao;
import com.example.healthapa.dao.apaDatabase;
import com.example.healthapa.entities.Activite;
import com.example.healthapa.entities.Parcours;
import com.example.healthapa.entities.Structure;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ListeStructure extends DialogFragment{

    FragmentManager fragmentManager;
    FloatingActionButton floatingActivityButton;

    StructureDao structureDao;
    private apaDatabase db;

    private RecyclerView mRecyclerView;
    private MonRecyclerViewAdapterStructure mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View view;

    CreateStructureFragment addStructureFragment = new CreateStructureFragment();

    public ListeStructure(){


    }

    public static ListeStructure newInstance(FragmentManager fragmentManager){
        ListeStructure listeStructure = new ListeStructure();
        listeStructure.fragmentManager = fragmentManager;
        return listeStructure;
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
        view = inflater.inflate(R.layout.liste_structure, container, false);

        // Add the following lines to create RecyclerView
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(false);

        //Exercice 1 on remplace la ligne crÃ©ant le LayoutManager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //mLayoutManager=new LinearLayoutManager(this,GridLayoutManager.VERTICAL, false);

        //mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MonRecyclerViewAdapterStructure(getContext(), getDataSource());
        mRecyclerView.setAdapter(mAdapter);


        floatingActivityButton = view.findViewById(R.id.floatingActionButton);
        floatingActivityButton.setOnClickListener(v -> {
            addStructureFragment = CreateStructureFragment.newInstance(fragmentManager);
            addStructureFragment.show(getActivity().getSupportFragmentManager(), "addStructureFragment");



        });

        return view;
    }

    private ArrayList<LinkedHashMap<String, String>> getDataSource(){

        ArrayList<LinkedHashMap<String, String>> structures = new ArrayList<>();

        new Thread(() -> {


            db = apaDatabase.getDatabase(getActivity().getApplicationContext());
            structureDao = db.structureDao();
            List<Structure> struc_list = structureDao.findAllStructure();

            for (Structure stc: struc_list)
            {
                LinkedHashMap<String, String> structure1 = new LinkedHashMap<>();


                structure1.put("nom", stc.getNom());
                structure1.put("discipline", stc.getDiscipline());
                structure1.put("pathologies", stc.getListePathology());
                structures.add(structure1);
            }

        }).start();

        return structures;

    }

}