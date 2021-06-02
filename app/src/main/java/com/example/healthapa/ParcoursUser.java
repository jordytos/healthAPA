package com.example.healthapa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.healthapa.dao.ActiviteDao;
import com.example.healthapa.dao.ParcoursDao;
import com.example.healthapa.dao.UtilisateurDao;
import com.example.healthapa.dao.apaDatabase;
import com.example.healthapa.entities.Activite;
import com.example.healthapa.entities.Parcours;
import com.example.healthapa.entities.Utilisateur;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ParcoursUser extends DialogFragment {

    FragmentManager fragmentManager;
    private TextView titre, categorie, descrip;

    CreateActiviteFragment addActivityFragment = new CreateActiviteFragment();

    private RecyclerView mRecyclerView;
    private MonRecyclerViewAdapterActivite mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View view;

    private FirebaseAuth mAuth;
    private ActiviteDao activiteDao;
    private apaDatabase db;
    private UtilisateurDao utilisateurDao;
    private ParcoursDao parcoursDao;



    public ParcoursUser(){


    }

    public static ParcoursUser newInstance(FragmentManager fragmentManager){
        ParcoursUser parcoursUser = new ParcoursUser();
        parcoursUser.fragmentManager = fragmentManager;
        return parcoursUser;
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
        view = inflater.inflate(R.layout.activity_parcours_user, container, false);

        titre = view.findViewById(R.id.titreParc);
        categorie = view.findViewById(R.id.categorieParc);
        descrip = view.findViewById(R.id.descriptionParc);

        //Parcours parc = new Parcours("Parcours de Jean","Marathon","Parcours pour le patient Jean qu'il doit impérativement suivre pour son bien-être", "Jean");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String currentUserEmail = currentUser.getEmail();

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

        new Thread(() -> {

            db = apaDatabase.getDatabase(getActivity().getApplicationContext());
            parcoursDao = db.parcoursDao();
            List<Parcours> parc_list = parcoursDao.findAllParcours();


            getActivity().runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    Parcours new_parcours = new Parcours();

                    for (Parcours parcours: parc_list
                    ) {
                        if(parcours.getPatient().equals(currentUserEmail)){
                            new_parcours = parcours;
                        }

                    }
                    titre.setText(new_parcours.getTitre());
                    categorie.setText(new_parcours.getCategory());
                    descrip.setText(new_parcours.getDescription());
                }
            });

        }).start();

        return view;
    }


    private ArrayList<LinkedHashMap<String, String>> getDataSource(){

        ArrayList<LinkedHashMap<String, String>> activites = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String currentUserEmail = currentUser.getEmail();

        new Thread(() -> {


            db = apaDatabase.getDatabase(getActivity().getApplicationContext());
            activiteDao = db.activiteDao();
            List<Activite> act_list = activiteDao.findActiviteByEmail(currentUserEmail);

            for (Activite act : act_list)
            {
                LinkedHashMap<String, String> activite1 = new LinkedHashMap<>();


                activite1.put("titre", act.getTitre());
                activite1.put("duree", act.getDuree());
                activite1.put("description", act.getDescription());
                activite1.put("structure", act.getStructure());
                activites.add(activite1);
            }
        }).start();

        return activites;

    }
}