package com.example.healthapa.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.healthapa.entities.Utilisateur;

@Dao
public interface UtilisateurDao {

    @Insert
    void insererUSer(Utilisateur utilisateur);
}
