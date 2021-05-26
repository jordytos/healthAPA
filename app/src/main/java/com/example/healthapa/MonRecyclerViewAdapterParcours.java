package com.example.healthapa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;


public class MonRecyclerViewAdapterParcours extends RecyclerView.Adapter<MonRecyclerViewAdapterParcours.ConteneurDeDonnee> {
    private Context context;
    private ArrayList<LinkedHashMap<String, String>> parcours;

    public MonRecyclerViewAdapterParcours(Context context, ArrayList<LinkedHashMap<String, String>> parcours) {
        this.context = context;
        this.parcours = parcours;
    }

    @Override
    public ConteneurDeDonnee onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_liste_parcours, parent, false);
        return new ConteneurDeDonnee(view);
    }

    @Override
    public void onBindViewHolder(ConteneurDeDonnee conteneur, int position) {
        conteneur.nom.setText(parcours.get(position).get("nom"));
        conteneur.categorie.setText(parcours.get(position).get("categorie"));
        conteneur.description.setText(parcours.get(position).get("description"));
    }

    @Override
    public int getItemCount() {
        return parcours.size();
    }

    public static class ConteneurDeDonnee extends RecyclerView.ViewHolder {
        TextView nom;
        TextView description;
        TextView categorie;

        public ConteneurDeDonnee(View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.parcours_nom);
            categorie = (TextView) itemView.findViewById(R.id.parcours_categorie);
            description = (TextView) itemView.findViewById(R.id.parcours_description);
        }


    }



}