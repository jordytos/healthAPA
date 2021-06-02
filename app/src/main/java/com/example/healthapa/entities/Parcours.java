package com.example.healthapa.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Parcours {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String titre;
    private String description;
    private String category;
    private String patient;

    public Parcours() {

    }


    public Parcours(String titre, String category,String description, String patient) {
        this.titre = titre;
        this.description = description;
        this.category = category;
        this.patient = patient;
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

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    @NonNull
    @Override
    public String toString() {
        return "Titre : " + titre + " , Catégorie : " +category + ", Description : " +description;
    }

}
