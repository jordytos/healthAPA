package com.example.healthapa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthapa.dao.UtilisateurDao;
import com.example.healthapa.dao.apaDatabase;
import com.example.healthapa.entities.Utilisateur;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ListeProfessionnel extends DialogFragment{

    FragmentManager fragmentManager;

    UtilisateurDao utilisateurDao;
    private apaDatabase db;

    private RecyclerView mRecyclerView;
    private MonRecyclerViewAdapterProfessionnel mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View view;

    public ListeProfessionnel(){


    }

    public static ListeProfessionnel newInstance(FragmentManager fragmentManager){
        ListeProfessionnel listePatient = new ListeProfessionnel();
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
        view = inflater.inflate(R.layout.liste_professionnel, container, false);

        // Add the following lines to create RecyclerView
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(false);

        //Exercice 1 on remplace la ligne crÃ©ant le LayoutManager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //mLayoutManager=new LinearLayoutManager(this,GridLayoutManager.VERTICAL, false);

        //mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MonRecyclerViewAdapterProfessionnel(getContext(), getDataSource());
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    private ArrayList<LinkedHashMap<String, String>> getDataSource(){

        ArrayList<LinkedHashMap<String, String>> profes = new ArrayList<>();

        new Thread(() -> {


            db = apaDatabase.getDatabase(getActivity().getApplicationContext());
            utilisateurDao = db.utilisateurDao();
            List<Utilisateur> pro_list = utilisateurDao.findProfesionnelsEmail();

            for (Utilisateur pt: pro_list)
            {
                LinkedHashMap<String, String> pro1 = new LinkedHashMap<>();


                pro1.put("nom", pt.getNom_user());
                pro1.put("prenom", pt.getPrenom_user());
                pro1.put("email", pt.getEmail());
                pro1.put("role", pt.getRole());
                pro1.put("telephone", pt.getTelephone());
                profes.add(pro1);
            }

        }).start();

        return profes;

    }

}