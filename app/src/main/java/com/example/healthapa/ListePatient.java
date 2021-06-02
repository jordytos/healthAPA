package com.example.healthapa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthapa.dao.StructureDao;
import com.example.healthapa.dao.UtilisateurDao;
import com.example.healthapa.dao.apaDatabase;
import com.example.healthapa.entities.Patient;
import com.example.healthapa.entities.Structure;
import com.example.healthapa.entities.Utilisateur;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ListePatient extends DialogFragment{

    FragmentManager fragmentManager;

    UtilisateurDao utilisateurDao;
    private apaDatabase db;

    private RecyclerView mRecyclerView;
    private MonRecyclerViewAdapterPatient mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View view;

    public ListePatient(){


    }

    public static ListePatient newInstance(FragmentManager fragmentManager){
        ListePatient listePatient = new ListePatient();
        listePatient.fragmentManager = fragmentManager;
        return listePatient;
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
        view = inflater.inflate(R.layout.liste_patient, container, false);

        // Add the following lines to create RecyclerView
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(false);

        //Exercice 1 on remplace la ligne crÃ©ant le LayoutManager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //mLayoutManager=new LinearLayoutManager(this,GridLayoutManager.VERTICAL, false);

        //mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MonRecyclerViewAdapterPatient(getContext(), getDataSource());
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    private ArrayList<LinkedHashMap<String, String>> getDataSource(){

        ArrayList<LinkedHashMap<String, String>> patients = new ArrayList<>();

        new Thread(() -> {


            db = apaDatabase.getDatabase(getActivity().getApplicationContext());
            utilisateurDao = db.utilisateurDao();
            List<Utilisateur> pati_list = utilisateurDao.findPatientsEmail();

            for (Utilisateur pt: pati_list)
            {
                LinkedHashMap<String, String> patient1 = new LinkedHashMap<>();


                patient1.put("nom", pt.getNom_user());
                patient1.put("prenom", pt.getPrenom_user());
                patient1.put("email", pt.getEmail());
                patients.add(patient1);
            }

        }).start();

        return patients;

    }

}