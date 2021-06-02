package com.example.healthapa;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthapa.dao.ActiviteDao;
import com.example.healthapa.dao.ParcoursDao;
import com.example.healthapa.dao.SeanceDao;
import com.example.healthapa.dao.UtilisateurDao;
import com.example.healthapa.dao.apaDatabase;
import com.example.healthapa.entities.Activite;
import com.example.healthapa.entities.Seance;
import com.example.healthapa.entities.Utilisateur;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ListeSeance extends DialogFragment{

    FragmentManager fragmentManager;

    SeanceDao seanceDao;
    private apaDatabase db;

    private FirebaseAuth mAuth;
    private UtilisateurDao utilisateurDao;
    private ParcoursDao parcoursDao;

    private RecyclerView mRecyclerView;
    private MonRecyclerViewAdapterSeance mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View view;

    public ListeSeance(){


    }

    public static ListeSeance newInstance(FragmentManager fragmentManager){
        ListeSeance listeSeance = new ListeSeance();
        listeSeance.fragmentManager = fragmentManager;
        return listeSeance;
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
        view = inflater.inflate(R.layout.liste_seance, container, false);

        // Add the following lines to create RecyclerView
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(false);

        //Exercice 1 on remplace la ligne crÃ©ant le LayoutManager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //mLayoutManager=new LinearLayoutManager(this,GridLayoutManager.VERTICAL, false);

        //mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MonRecyclerViewAdapterSeance(getContext(), getDataSource());
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    private ArrayList<LinkedHashMap<String, String>> getDataSource(){

        ArrayList<LinkedHashMap<String, String>> seances = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String currentUserEmail = currentUser.getEmail();

        new Thread(() -> {

            db = apaDatabase.getDatabase(getActivity().getApplicationContext());
            seanceDao = db.seanceDao();
            List<Seance> sec_list = seanceDao.findSeanceByEmail(currentUserEmail);

            for (Seance sc: sec_list)
            {
                LinkedHashMap<String, String> seance1 = new LinkedHashMap<>();

                seance1.put("nom", sc.getPatient());
                seance1.put("duree", sc.getDuree());
                seance1.put("date", sc.getDateTime());
                seances.add(seance1);
            }

        }).start();

        return seances;

    }

}