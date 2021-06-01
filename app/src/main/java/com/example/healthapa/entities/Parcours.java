package com.example.healthapa.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Parcours {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String titre;
    private String description;
    private String category;

    //private Docteur docteur;
    //private List<Activite> activites;

    public Parcours() {

    }

    /*public Parcours(String titre, String description, String category, Docteur docteur, List<Activite> activites) {
        this.titre = titre;
        this.description = description;
        this.category = category;
        //this.docteur = docteur;
        this.activites = activites;
    }*/

    public Parcours(String titre, String category,String description) {
        this.titre = titre;
        this.description = description;
        this.category = category;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @NonNull
    @Override
    public String toString() {
        return "Titre : " + titre + " , Catégorie : " +category + ", Description : " +description;
    }

    /*public Docteur getDocteur() {
        return docteur;
    }

    public void setDocteur(Docteur docteur) {
        this.docteur = docteur;
    }

    public List<Activite> getActivites() {
        return activites;
    }

    public void setActivites(List<Activite> activites) {
        this.activites = activites;
    }*/
}
