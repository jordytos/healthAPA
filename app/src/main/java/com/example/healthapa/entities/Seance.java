package com.example.healthapa.entities;

import java.time.LocalDateTime;

public class Seance {

    private static Long uid = 1L;

    private Long id;
    private LocalDateTime dateTime;
    private Integer duree;
    private String rapport;
    private Intervenant intervenant;
    private Patient patient;

    public Seance() {
        setId(uid);
        uid++;
    }

    public Seance(LocalDateTime dateTime, Integer duree, String rapport, Intervenant intervenant, Patient patient) {
        this.dateTime = dateTime;
        this.duree = duree;
        this.rapport = rapport;
        this.intervenant = intervenant;
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public String getRapport() {
        return rapport;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    public Intervenant getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(Intervenant intervenant) {
        this.intervenant = intervenant;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
