package com.example.healthapa.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.healthapa.entities.Parcours;

import java.util.List;


@Dao
public interface ParcoursDao {

    @Insert
    void insererParcours(Parcours parcours);

    @Query("SELECT * FROM Parcours")
    List<Parcours> findAllParcours();

}
