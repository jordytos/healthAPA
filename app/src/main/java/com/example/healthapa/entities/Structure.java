package com.example.healthapa.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Structure {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String nom;
    private String discipline;
    private String listePathology;

    public Structure() {

    }

    public Structure(String nom, String discipline, String listePathology) {
        this.nom = nom;
        this.discipline = discipline;
        this.listePathology = listePathology;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getListePathology() {
        return listePathology;
    }

    public void setListePathology(String listePathology) {
        this.listePathology = listePathology;
    }

}
