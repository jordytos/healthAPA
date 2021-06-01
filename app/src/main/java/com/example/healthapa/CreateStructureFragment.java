package com.example.healthapa;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.healthapa.dao.StructureDao;
import com.example.healthapa.dao.apaDatabase;
import com.example.healthapa.entities.Structure;

import java.util.ArrayList;
import java.util.List;


public class CreateStructureFragment extends DialogFragment {
    FragmentManager fm;

    EditText titleEditText, disciplineEditText, pathologyEditText;
    Button addStructureButton;
    List<Structure> listStructures = new ArrayList<Structure>();

    StructureDao structureDao;
    private apaDatabase db;

    public CreateStructureFragment() {

    }

    public static CreateStructureFragment newInstance(FragmentManager fm) {
        CreateStructureFragment fragment = new CreateStructureFragment();
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
        View view = inflater.inflate(R.layout.create_structure, container, false);

        titleEditText = view.findViewById(R.id.titleStructure);
        disciplineEditText = view.findViewById(R.id.disciplineStructure);
        pathologyEditText = view.findViewById(R.id.pathologieStructure);

        addStructureButton = view.findViewById(R.id.addStructureButton);

        Structure structure = new Structure();
        db = apaDatabase.getDatabase(getActivity().getApplicationContext());
        structureDao = db.structureDao();

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (titleEditText.getText().toString().isEmpty()
                        || disciplineEditText.getText().toString().isEmpty()
                        || pathologyEditText.getText().toString().isEmpty()) {
                    addStructureButton.setEnabled(false);
                } else {
                    addStructureButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
        titleEditText.addTextChangedListener(textWatcher);
        disciplineEditText.addTextChangedListener(textWatcher);
        pathologyEditText.addTextChangedListener(textWatcher);

        addStructureButton.setOnClickListener(v -> {

            String titre = titleEditText.getText().toString().trim();
            String discip = disciplineEditText.getText().toString().trim();
            String path = pathologyEditText.getText().toString().trim();

            new Thread(new Runnable(){
                @Override
                public void run() {
                    structure.setNom(titre);
                    structure.setDiscipline(discip);
                    structure.setListePathology(path);
                    Log.d("Message : ", structure.toString());
                    try {
                        structureDao.insererStructure(structure);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    getActivity().runOnUiThread(new Runnable(){
                        @Override
                        public void run() {
                            Toast.makeText(getActivity().getApplicationContext(), "Strucutre Successfully added !!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).start();

        });

        return view;
    }
}