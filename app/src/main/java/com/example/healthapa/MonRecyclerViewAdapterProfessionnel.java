package com.example.healthapa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;


public class MonRecyclerViewAdapterProfessionnel extends RecyclerView.Adapter<MonRecyclerViewAdapterProfessionnel.ConteneurDeDonnee> {
    private Context context;
    private ArrayList<LinkedHashMap<String, String>> professionnels;

    public MonRecyclerViewAdapterProfessionnel(Context context, ArrayList<LinkedHashMap<String, String>> professionnels) {
        this.context = context;
        this.professionnels = professionnels;
    }

    @Override
    public ConteneurDeDonnee onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_liste_professionnel, parent, false);
        return new ConteneurDeDonnee(view);
    }

    @Override
    public void onBindViewHolder(ConteneurDeDonnee conteneur, int position) {
        conteneur.nom.setText(professionnels.get(position).get("nom"));
        conteneur.prenom.setText(professionnels.get(position).get("prenom"));
        conteneur.email.setText(professionnels.get(position).get("email"));
        conteneur.role.setText(professionnels.get(position).get("role"));
        conteneur.telephone.setText(professionnels.get(position).get("telephone"));
    }

    @Override
    public int getItemCount() {
        return professionnels.size();
    }

    public static class ConteneurDeDonnee extends RecyclerView.ViewHolder {
        TextView nom;
        TextView prenom;
        TextView email;
        TextView role;
        TextView telephone;

        public ConteneurDeDonnee(View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.prof_nom);
            prenom = (TextView) itemView.findViewById(R.id.prof_prenom);
            email = (TextView) itemView.findViewById(R.id.prof_email);
            role = (TextView) itemView.findViewById(R.id.prof_role);
            telephone = (TextView) itemView.findViewById(R.id.prof_telephone);
        }


    }



}