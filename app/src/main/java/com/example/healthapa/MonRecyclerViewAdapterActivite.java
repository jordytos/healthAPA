package com.example.healthapa;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;


public class MonRecyclerViewAdapterActivite extends RecyclerView.Adapter<com.example.healthapa.MonRecyclerViewAdapterActivite.ConteneurDeDonnee> {
    private Context context;
    private ArrayList<LinkedHashMap<String, String>> activites;

    public MonRecyclerViewAdapterActivite(Context context, ArrayList<LinkedHashMap<String, String>> activites) {
        this.context = context;
        this.activites = activites;
    }

    @Override
    public ConteneurDeDonnee onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_liste_activite, parent, false);
        return new ConteneurDeDonnee(view);
    }

    @Override
    public void onBindViewHolder(ConteneurDeDonnee conteneur, int position) {
        conteneur.titre.setText(activites.get(position).get("titre"));
        conteneur.duree.setText(activites.get(position).get("duree") + " " + context.getString(R.string.heures));
        conteneur.description.setText(activites.get(position).get("description"));
        conteneur.structure.setText(activites.get(position).get("structure"));
    }

    @Override
    public int getItemCount() {
        return activites.size();
    }

    public static class ConteneurDeDonnee extends RecyclerView.ViewHolder {
        TextView titre;
        TextView duree;
        TextView description;
        TextView structure;

        public ConteneurDeDonnee(View itemView) {
            super(itemView);
            titre = (TextView) itemView.findViewById(R.id.activite_titre);
            duree = (TextView) itemView.findViewById(R.id.activite_duree);
            description = (TextView) itemView.findViewById(R.id.activite_description);
            structure = (TextView) itemView.findViewById(R.id.activite_structure);
        }


    }



}