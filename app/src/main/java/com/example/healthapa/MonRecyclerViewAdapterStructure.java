package com.example.healthapa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;


public class MonRecyclerViewAdapterStructure extends RecyclerView.Adapter<MonRecyclerViewAdapterStructure.ConteneurDeDonnee> {
    private Context context;
    private ArrayList<LinkedHashMap<String, String>> structures;

    public MonRecyclerViewAdapterStructure(Context context, ArrayList<LinkedHashMap<String, String>> structures) {
        this.context = context;
        this.structures = structures;
    }

    @Override
    public ConteneurDeDonnee onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_liste_structure, parent, false);
        return new ConteneurDeDonnee(view);
    }

    @Override
    public void onBindViewHolder(ConteneurDeDonnee conteneur, int position) {
        conteneur.titre.setText(structures.get(position).get("titre"));
        conteneur.discipline.setText(structures.get(position).get("discipline"));
        conteneur.Pathologies.setText(structures.get(position).get("Pathologies"));
    }

    @Override
    public int getItemCount() {
        return structures.size();
    }

    public static class ConteneurDeDonnee extends RecyclerView.ViewHolder {
        TextView titre;
        TextView discipline;
        TextView Pathologies;

        public ConteneurDeDonnee(View itemView) {
            super(itemView);
            titre = (TextView) itemView.findViewById(R.id.titleStructure);
            discipline = (TextView) itemView.findViewById(R.id.disciplineStructure);
            Pathologies = (TextView) itemView.findViewById(R.id.pathologieStructure);
        }


    }



}