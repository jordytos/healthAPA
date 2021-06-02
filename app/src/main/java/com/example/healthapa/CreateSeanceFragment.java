package com.example.healthapa;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.healthapa.dao.ActiviteDao;
import com.example.healthapa.dao.SeanceDao;
import com.example.healthapa.dao.StructureDao;
import com.example.healthapa.dao.UtilisateurDao;
import com.example.healthapa.dao.apaDatabase;
import com.example.healthapa.entities.Activite;
import com.example.healthapa.entities.Seance;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class CreateSeanceFragment extends DialogFragment {
    FragmentManager fm;

    EditText dureeEditText, dateEditText;
    Button addSeanceButton;

    SeanceDao seanceDao;
    private apaDatabase db;


    ArrayAdapter<String> adapterPatient;
    List<String> listePatients;
    UtilisateurDao utilisateurDao;
    ListView listViewDataPat;


    public CreateSeanceFragment() {
    }

    public static CreateSeanceFragment newInstance(FragmentManager fm) {
        CreateSeanceFragment fragment = new CreateSeanceFragment();
        fragment.fm = fm;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.create_seance, container, false);

        setHasOptionsMenu(true);

        dureeEditText = view.findViewById(R.id.dureeSeance);
        dateEditText = view.findViewById(R.id.dateSeance);
        addSeanceButton = view.findViewById(R.id.addSeanceButton);
        listViewDataPat = view.findViewById(R.id.listViewSeancePat);

        Seance seance = new Seance();
        db = apaDatabase.getDatabase(getActivity().getApplicationContext());
        utilisateurDao = db.utilisateurDao();
        seanceDao = db.seanceDao();


        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    listePatients = utilisateurDao.findByEmailAllPatients();

                    adapterPatient = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_multiple_choice,listePatients);

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
                if (dureeEditText.getText().toString().isEmpty()
                        || dateEditText.getText().toString().isEmpty()) {
                    addSeanceButton.setEnabled(false);
                } else {
                    addSeanceButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
        dureeEditText.addTextChangedListener(textWatcher);
        dateEditText.addTextChangedListener(textWatcher);


        //ADD BUTTON
        addSeanceButton.setOnClickListener(v -> {

            String duree = dureeEditText.getText().toString().trim();
            String date = dateEditText.getText().toString().trim();

            new Thread(new Runnable(){
                @Override
                public void run() {
                        String patient ="";
                    for (int i = 0; i < listViewDataPat.getCount(); i++){
                        if(listViewDataPat.isItemChecked(i) == true){
                            patient = String.valueOf(listViewDataPat.getItemAtPosition(i)).trim();
                        }
                    }

                    seance.setDuree(duree);
                    seance.setDateTime(date);
                    seance.setPatient(patient);
                    seanceDao.insererSeance(seance);

                    getActivity().runOnUiThread(new Runnable(){
                        @Override
                        public void run() {
                            Toast.makeText(getActivity().getApplicationContext(), "Séance Successfully added !!", Toast.LENGTH_SHORT).show();
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