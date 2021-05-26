package com.example.healthapa;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.healthapa.entities.Activite;

import java.util.ArrayList;
import java.util.List;


public class CreateActiviteFragment extends DialogFragment {
    FragmentManager fm;
    ListeActivite listeActivite;

    EditText titleEditText, descriptionEditText, durationEditText, structureEditText;
    Button addActivityButton;
    List<Activite> listActivites = new ArrayList<Activite>();


   /* public CreateActiviteFragment() {
        // Required empty public constructor

    }*/

    public CreateActiviteFragment() {

        Activite a1 = new Activite("Natation","description\", \"Lorem ipsum dolor sit amet coko lier de la verginier manteau soyeux","1h");
        Activite a2 = new Activite("Course","Run Run","2h");
        Activite a3 = new Activite("Marche","description\", \"Marchons parlons volons ok ok ronaldinho gaucho joueur foot ronalldo messi goat et fraude pessi hurle miaule aboie miaouw neymar jr mbappe lea tortue ok pd cough cough","3h");
        Activite a4 = new Activite("Course à pied","Randonnée et marche le long du lac","30 min");

        this.listActivites.add(a1);
        this.listActivites.add(a2);
        this.listActivites.add(a3);
        this.listActivites.add(a4);

    }

    //GETTER
    public List<Activite> getListActivites() {
        return listActivites;
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddActivityFragment.
     */
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

        titleEditText = view.findViewById(R.id.titleActivite);
        descriptionEditText = view.findViewById(R.id.descriptionActivite);
        durationEditText = view.findViewById(R.id.durationActivite);
        structureEditText = view.findViewById(R.id.structureActivite);
        addActivityButton = view.findViewById(R.id.addActivityButton);

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

        structureEditText.setOnClickListener(v -> {
            ListeActivite chooseStructureFragment = ListeActivite.newInstance(fm);
            chooseStructureFragment.show(fm, "chooseStructureFragment");
        });

        addActivityButton.setOnClickListener(v -> {

            String titre = titleEditText.getText().toString();
            String descrip = descriptionEditText.getText().toString();
            String duree = durationEditText.getText().toString();
            String struct = structureEditText.getText().toString();

            /*Log.d("succes","Titre "+titre);
            Log.d("succes","Descrip "+descrip);
            Log.d("succes","Duree "+duree);*/

            listActivites.add(new Activite(titre,descrip,duree));



            int cnt = 0;
            for (Activite ac: this.listActivites)
            {

                cnt++;
                String count = String.valueOf(cnt);

                Log.d("succes","---> Activité n°"+cnt);
                Log.d("succes","Titre "+ac.getTitre());
                Log.d("succes","Descrip "+ac.getDescription());
                Log.d("succes","Duree "+ac.getDuree());

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