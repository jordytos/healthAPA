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
import com.example.healthapa.entities.Parcours;

import java.util.ArrayList;
import java.util.List;


public class CreateParcoursFragment extends DialogFragment {
    FragmentManager fm;
    ListeParcours listeParcours;
    EditText nomEditText, descriptionEditText, categorieEditText, activiteEditText;
    Button addParcoursButton;
    List<Parcours> listParcours = new ArrayList<Parcours>();


   /* public CreateActiviteFragment() {
        // Required empty public constructor

    }*/

    public CreateParcoursFragment() {

        Parcours p1 = new Parcours("Parcours 1","AVC","Ok");
        Parcours p2 = new Parcours("Parcours 2","Mal de dos","Alright");
        Parcours p3 = new Parcours("Parcours 3","Arthrose","De Accuerdo");
        Parcours p4 = new Parcours("Parcours 4","Ligaments croisées","Football");

        this.listParcours.add(p1);
        this.listParcours.add(p2);
        this.listParcours.add(p3);
        this.listParcours.add(p4);

    }

    //GETTER
    public List<Parcours> getListParcours() {
        return listParcours;
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddActivityFragment.
     */
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

        nomEditText = view.findViewById(R.id.nomParcours);
        categorieEditText = view.findViewById(R.id.categorieParcours);
        descriptionEditText = view.findViewById(R.id.descriptionParcours);
        addParcoursButton = view.findViewById(R.id.addParcoursButton);

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

            String titre = nomEditText.getText().toString();
            String descrip = descriptionEditText.getText().toString();
            String categorie = categorieEditText.getText().toString();
            String activite = activiteEditText.getText().toString();

            /*Log.d("succes","Titre "+titre);
            Log.d("succes","Descrip "+descrip);
            Log.d("succes","Duree "+duree);*/

            listParcours.add(new Parcours(titre,categorie,descrip));



            int cnt = 0;
            for (Parcours pc: this.listParcours)
            {

                cnt++;
                String count = String.valueOf(cnt);

                Log.d("succes","---> Parcours n°"+cnt);
                Log.d("succes","Nom "+pc.getTitre());
                Log.d("succes","Categorie "+pc.getCategory());
                Log.d("succes","Description "+pc.getDescription());

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