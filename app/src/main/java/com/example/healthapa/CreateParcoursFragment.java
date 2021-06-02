package com.example.healthapa;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.healthapa.dao.ActiviteDao;
import com.example.healthapa.dao.ParcoursDao;
import com.example.healthapa.dao.UtilisateurDao;
import com.example.healthapa.dao.apaDatabase;
import com.example.healthapa.entities.Parcours;

import java.util.List;


public class CreateParcoursFragment extends DialogFragment {
    ListView listViewData;
    ListView listViewDataPat;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapterPatient;
    List<String> listeActivites;
    List<String> listePatients;

    FragmentManager fm;
    EditText nomEditText, descriptionEditText, categorieEditText, activiteEditText;
    Button addParcoursButton;

    ParcoursDao parcoursDao;
    ActiviteDao activiteDao;
    UtilisateurDao utilisateurDao;
    private apaDatabase db;

    public CreateParcoursFragment() {

    }

    public static CreateParcoursFragment newInstance(FragmentManager fm) {
        CreateParcoursFragment fragment = new CreateParcoursFragment();
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
        View view = inflater.inflate(R.layout.create_parcours, container, false);

        setHasOptionsMenu(true);

        nomEditText = view.findViewById(R.id.nomParcours);
        categorieEditText = view.findViewById(R.id.categorieParcours);
        descriptionEditText = view.findViewById(R.id.descriptionParcours);
        addParcoursButton = view.findViewById(R.id.addParcoursButton);
        listViewData = view.findViewById(R.id.listViewAct);
        listViewDataPat = view.findViewById(R.id.listViewPat);

        Parcours parcours = new Parcours();
        db = apaDatabase.getDatabase(getActivity().getApplicationContext());
        parcoursDao = db.parcoursDao();
        activiteDao = db.activiteDao();
        utilisateurDao = db.utilisateurDao();

        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    listeActivites = activiteDao.findByNameAllActivite();
                    listePatients = utilisateurDao.findByEmailAllPatients();

                    adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_multiple_choice,listeActivites);
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
                if (nomEditText.getText().toString().isEmpty()
                        || descriptionEditText.getText().toString().isEmpty()
                        || categorieEditText.getText().toString().isEmpty()) {
                    addParcoursButton.setEnabled(false);
                } else {
                    addParcoursButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
        nomEditText.addTextChangedListener(textWatcher);
        descriptionEditText.addTextChangedListener(textWatcher);
        categorieEditText.addTextChangedListener(textWatcher);

       /* activiteEditText.setOnClickListener(v -> {
            ListeParcours chooseStructureFragment = ListeParcours.newInstance(fm);
            chooseStructureFragment.show(fm, "chooseStructureFragment");
        });*/

        addParcoursButton.setOnClickListener(v -> {
            String titre = nomEditText.getText().toString().trim();
            String descrip = descriptionEditText.getText().toString().trim();
            String categorie = categorieEditText.getText().toString().trim();
            //String activite = activiteEditText.getText().toString();


            new Thread(new Runnable(){
                @Override
                public void run() {
                    String patient ="";
                    for (int i = 0; i < listViewDataPat.getCount(); i++){
                        if(listViewDataPat.isItemChecked(i) == true){
                            patient = String.valueOf(listViewDataPat.getItemAtPosition(i)).trim();
                        }
                    }

                    parcours.setTitre(titre);
                    parcours.setCategory(categorie);
                    parcours.setDescription(descrip);
                    parcours.setPatient(patient);
                    Log.d("Message : ", parcours.toString());
                    try {
                        parcoursDao.insererParcours(parcours);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.d("AFTER INSERT","ENTERED");
                    getActivity().runOnUiThread(new Runnable(){
                        @Override
                        public void run() {
                            Toast.makeText(getActivity().getApplicationContext(), "Parcours Successfully added !!", Toast.LENGTH_SHORT).show();
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListeParcours()).commit();
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