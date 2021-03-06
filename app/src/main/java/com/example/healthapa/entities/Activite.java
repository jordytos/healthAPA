package com.example.healthapa.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Activite {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String titre;
    private String description;
    private String duree;
    private String structure;
    private String patient;

    public Activite() {

    }

   public Activite(String titre, String description,String duree, String structure, String patient) {
        this.titre = titre;
        this.description = description;
        this.duree = duree;
        this.structure = structure;
       this.patient = patient;
    }

    public Activite(String titre, String description,String duree) {
        this.titre = titre;
        this.description = description;
        this.duree = duree;

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

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }
}