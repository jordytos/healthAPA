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

    @Query("SELECT * FROM Utilisateur WHERE email =:email")
    List<Utilisateur> findByEmail(String email);

    @Query("SELECT * FROM Utilisateur WHERE role ='Patient' ")
    List<Utilisateur> findPatientsEmail();

    @Query("SELECT * FROM Utilisateur WHERE role ='Médecin' OR role='Intervenant' ")
    List<Utilisateur> findProfesionnelsEmail();

    @Query("SELECT email FROM Utilisateur WHERE role='Patient' ")
    List<String> findByEmailAllPatients();
}
