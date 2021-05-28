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

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.healthapa.entities.Activite;
import com.example.healthapa.entities.Structure;

import java.util.ArrayList;
import java.util.List;


public class CreateStructureFragment extends DialogFragment {
    FragmentManager fm;

    EditText titleEditText, disciplineEditText, pathologyEditText;
    Button addStructureButton;
    List<Structure> listStructures = new ArrayList<Structure>();


    public CreateStructureFragment() {

        Structure s1 = new Structure("Centre pse", "Natation", "Rhumatisme");
        Structure s2 = new Structure("Colpo Datz", "Course", "Ligaments");
        Structure s3 = new Structure("Pôle AEK", "Saut", "Ligaments");
        Structure s4 = new Structure("Maison Wu Chen", "Yoga", "Cerveau");


        this.listStructures.add(s1);
        this.listStructures.add(s2);
        this.listStructures.add(s3);
        this.listStructures.add(s4);

    }

    //GETTER
    public List<Structure> getListStructures() {
        return listStructures;
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddActivityFragment.
     */
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

            String titre = titleEditText.getText().toString();
            String discip = disciplineEditText.getText().toString();
            String path = pathologyEditText.getText().toString();

            /*Log.d("succes","Titre "+titre);
            Log.d("succes","Descrip "+descrip);
            Log.d("succes","Duree "+duree);*/

            listStructures.add(new Structure(titre,discip,path));



            int cnt = 0;
            for (Structure ac: this.listStructures)
            {

                cnt++;
                String count = String.valueOf(cnt);

                Log.d("succes","---> Strcutre n°"+cnt);
                Log.d("succes","Titre "+ac.getNom());
                Log.d("succes","Discipline "+ac.getDiscipline());
                Log.d("succes","Pathologies "+ac.getListePathology());

                /*activite1.put("titre", ac.getTitre());
                activite1.put("duree", ac.getDuree());
                activite1.put("description", ac.getDescription());
                activite1.put("structure", "SOON...");
                activites.add(activite1);*/
            }


        });

        return view;
    }
}