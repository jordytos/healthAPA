package com.example.healthapa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;


public class MonRecyclerViewAdapterPatient extends RecyclerView.Adapter<MonRecyclerViewAdapterPatient.ConteneurDeDonnee> {
    private Context context;
    private ArrayList<LinkedHashMap<String, String>> patients;

    public MonRecyclerViewAdapterPatient(Context context, ArrayList<LinkedHashMap<String, String>> patients) {
        this.context = context;
        this.patients = patients;
    }

    @Override
    public ConteneurDeDonnee onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_liste_patient, parent, false);
        return new ConteneurDeDonnee(view);
    }

    @Override
    public void onBindViewHolder(ConteneurDeDonnee conteneur, int position) {
        conteneur.nom.setText(patients.get(position).get("nom"));
        conteneur.prenom.setText(patients.get(position).get("prenom"));
        conteneur.email.setText(patients.get(position).get("email"));
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public static class ConteneurDeDonnee extends RecyclerView.ViewHolder {
        TextView nom;
        TextView prenom;
        TextView email;

        public ConteneurDeDonnee(View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.patient_nom);
            prenom = (TextView) itemView.findViewById(R.id.patient_prenom);
            email = (TextView) itemView.findViewById(R.id.patient_email);
        }


    }



}