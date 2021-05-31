package com.example.healthapa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.healthapa.entities.Activite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActiviteShow extends Fragment {

    FragmentManager fragmentManager;
    TextView titre, duree, descrip;




    private RecyclerView mRecyclerView;
    private MonRecyclerViewAdapterParcours mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View view;



    public ActiviteShow(){


    }

    public static ActiviteShow newInstance(FragmentManager fragmentManager){
        ActiviteShow activiteShow = new ActiviteShow();
        activiteShow.fragmentManager = fragmentManager;
        return activiteShow;
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
        view = inflater.inflate(R.layout.activity_activite_show, container, false);

        titre = view.findViewById(R.id.titreAct);
        duree = view.findViewById(R.id.dureeAct);
        descrip = view.findViewById(R.id.descriptionAct);

        Activite activite = new Activite("Natation","Nage Libre, Tu dois savoir pratiquer de la natation à la fin de ce programme intense !","10h");

        titre.setText(activite.getTitre());
        duree.setText(activite.getDuree());
        descrip.setText(activite.getDescription());


        return view;
    }
}