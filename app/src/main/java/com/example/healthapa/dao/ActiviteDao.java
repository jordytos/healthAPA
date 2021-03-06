package com.example.healthapa.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.healthapa.entities.Activite;
import com.example.healthapa.entities.Parcours;

import java.util.List;

@Dao
public interface ActiviteDao {

    @Insert
    void insererActivite(Activite activite);

    @Query("SELECT * FROM Activite")
    List<Activite> findAllActivite();

    @Query("SELECT titre FROM Activite")
    List<String> findByNameAllActivite();

    @Query("SELECT * FROM Activite WHERE patient =:email")
    List<Activite> findActiviteByEmail(String email);

}
