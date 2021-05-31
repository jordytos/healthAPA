package com.example.healthapa.entities;

public class Intervenant {

    private static Long intervenantUid = 1L;

    private Long intervenantId;

    public Intervenant() {
        super();
        setIntervenantId(intervenantUid);
        intervenantUid++;
    }

    public Long getIntervenantId() {
        return intervenantId;
    }

    public void setIntervenantId(Long intervenantId) {
        this.intervenantId = intervenantId;
    }
}
