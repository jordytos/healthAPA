package com.example.healthapa.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.healthapa.entities.Seance;

import java.util.List;

@Dao
public interface SeanceDao {

    @Insert
    void insererSeance(Seance seance);

    @Query("SELECT * FROM Seance")
    List<Seance> findAllSeance();

    @Query("SELECT * FROM Seance WHERE patient =:email")
    List<Seance> findSeanceByEmail(String email);

}
