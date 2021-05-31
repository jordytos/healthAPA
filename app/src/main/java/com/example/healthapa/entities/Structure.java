package com.example.healthapa.entities;

public class Structure {

    private static Long uid = 1L;

    private Long id;
    private String nom;
    private String discipline;
    private String listePathology;

    public Structure() {
        setId(uid);
        uid++;
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
