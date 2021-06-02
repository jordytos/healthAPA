package com.example.healthapa.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity
public class Seance {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String dateTime;
    private String duree;
    private String patient;

    public Seance() {

    }

    public Seance(String dateTime, String duree, String patient) {
        this.dateTime = dateTime;
        this.duree = duree;
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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
