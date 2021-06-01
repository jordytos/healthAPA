package com.example.healthapa.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.healthapa.entities.Utilisateur;

import java.util.List;

@Dao
public interface UtilisateurDao {

    @Insert
    void insererUSer(Utilisateur utilisateur);

    @Query("SELECT * FROM Utilisateur")
    List<Utilisateur> findAllUser();

    @Query("SELECT * FROM Utilisateur WHERE email =:email LIMIT 1")
    Utilisateur findByEmail(String email);
}
