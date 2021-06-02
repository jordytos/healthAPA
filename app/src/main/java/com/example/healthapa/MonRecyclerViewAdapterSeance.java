package com.example.healthapa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;


public class MonRecyclerViewAdapterSeance extends RecyclerView.Adapter<MonRecyclerViewAdapterSeance.ConteneurDeDonnee> {
    private Context context;
    private ArrayList<LinkedHashMap<String, String>> seances;

    public MonRecyclerViewAdapterSeance(Context context, ArrayList<LinkedHashMap<String, String>> seances) {
        this.context = context;
        this.seances = seances;
    }

    @Override
    public ConteneurDeDonnee onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_liste_seance, parent, false);
        return new ConteneurDeDonnee(view);
    }

    @Override
    public void onBindViewHolder(ConteneurDeDonnee conteneur, int position) {
        conteneur.titre.setText(seances.get(position).get("nom"));
        conteneur.duree.setText(seances.get(position).get("duree"));
        conteneur.date.setText(seances.get(position).get("date"));
    }

    @Override
    public int getItemCount() {
        return seances.size();
    }

    public static class ConteneurDeDonnee extends RecyclerView.ViewHolder {
        TextView titre;
        TextView duree;
        TextView date;

        public ConteneurDeDonnee(View itemView) {
            super(itemView);
            titre = (TextView) itemView.findViewById(R.id.seancePat_nom);
            duree = (TextView) itemView.findViewById(R.id.seance_heure);
            date = (TextView) itemView.findViewById(R.id.seance_date);
        }


    }



}