package com.example.healthapa;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthapa.dao.ActiviteDao;
import com.example.healthapa.dao.ParcoursDao;
import com.example.healthapa.dao.StructureDao;
import com.example.healthapa.dao.UtilisateurDao;
import com.example.healthapa.dao.apaDatabase;
import com.example.healthapa.entities.Activite;
import com.example.healthapa.entities.Parcours;

import java.util.ArrayList;
import java.util.List;


public class CreateActiviteFragment extends DialogFragment {
    ListView listViewData;
    ArrayAdapter<String> adapter;
    List<String> listeStructures;

    FragmentManager fm;

    EditText titleEditText, descriptionEditText, durationEditText;
    Button addActivityButton;

    ActiviteDao activiteDao;
    StructureDao structureDao;
    private apaDatabase db;


    ArrayAdapter<String> adapterPatient;
    List<String> listePatients;
    UtilisateurDao utilisateurDao;
    ListView listViewDataPat;


    public CreateActiviteFragment() {
    }

    public static CreateActiviteFragment newInstance(FragmentManager fm) {
        CreateActiviteFragment fragment = new CreateActiviteFragment();
        fragment.fm = fm;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.create_activite, container, false);

        setHasOptionsMenu(true);

        titleEditText = view.findViewById(R.id.titleActivite);
        descriptionEditText = view.findViewById(R.id.descriptionActivite);
        durationEditText = view.findViewById(R.id.durationActivite);
        addActivityButton = view.findViewById(R.id.addActivityButton);
        listViewData = view.findViewById(R.id.listViewStruct);
        listViewDataPat = view.findViewById(R.id.listViewActPat);

        Activite activite = new Activite();
        db = apaDatabase.getDatabase(getActivity().getApplicationContext());
        activiteDao = db.activiteDao();
        structureDao = db.structureDao();
        utilisateurDao = db.utilisateurDao();


        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    listeStructures = structureDao.findByNameAllStructure();
                    listePatients = utilisateurDao.findByEmailAllPatients();

                    adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_multiple_choice,listeStructures);
                    adapterPatient = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_multiple_choice,listePatients);


                    listViewData.setAdapter(adapter);
                    listViewDataPat.setAdapter(adapterPatient);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();






        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (titleEditText.getText().toString().isEmpty()
                        || descriptionEditText.getText().toString().isEmpty()
                        || durationEditText.getText().toString().isEmpty()) {
                    addActivityButton.setEnabled(false);
                } else {
                    addActivityButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
        titleEditText.addTextChangedListener(textWatcher);
        descriptionEditText.addTextChangedListener(textWatcher);
        durationEditText.addTextChangedListener(textWatcher);





        //ADD BUTTON
        addActivityButton.setOnClickListener(v -> {

            String titre = titleEditText.getText().toString().trim();
            String descrip = descriptionEditText.getText().toString().trim();
            String duree = durationEditText.getText().toString().trim();


            new Thread(new Runnable(){
                @Override
                public void run() {
                    String structure ="";
                    for (int i = 0; i < listViewData.getCount(); i++){
                        if(listViewData.isItemChecked(i) == true){
                            structure = String.valueOf(listViewData.getItemAtPosition(i)).trim();
                        }
                    }

                    String patient ="";
                    for (int i = 0; i < listViewDataPat.getCount(); i++){
                        if(listViewDataPat.isItemChecked(i) == true){
                            patient = String.valueOf(listViewDataPat.getItemAtPosition(i)).trim();
                        }
                    }

                    activite.setTitre(titre);
                    activite.setDuree(duree);
                    activite.setDescription(descrip);
                    activite.setStructure(structure);
                    activite.setPatient(patient);
                    activiteDao.insererActivite(activite);

                    getActivity().runOnUiThread(new Runnable(){
                        @Override
                        public void run() {
                            Toast.makeText(getActivity().getApplicationContext(), "Parcours Successfully added !!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).start();

        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);

    }



}