package com.example.healthapa.entities;

public class Docteur {

    private static Long docteurUid = 1L;

    private Long docteurId;

    public Docteur() {
        super();
        setDocteurId(docteurUid);
        docteurUid++;
    }

    public Long getDocteurId() {
        return docteurId;
    }

    public void setDocteurId(Long docteurId) {
        this.docteurId = docteurId;
    }

}
