package com.example.healthapa.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity
public class Utilisateur {

    @PrimaryKey(autoGenerate = true)
    private int user_id;

    private String nom_user;
    private String prenom_user;
    private int age;
    private String email;
    private String password;
    private String role;
    private String telephone;
    private int taille;
    private int poids;


    public Utilisateur(){

    }

    public Utilisateur(String nom_user, String prenom_user, int age, String email, String password, String role, String telephone, int taille, int poids) {
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.age = age;
        this.email = email;
        this.password = password;
        this.role = role;
        this.telephone = telephone;
        this.taille = taille;
        this.poids = poids;
    }



    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public String getPrenom_user() {
        return prenom_user;
    }

    public void setPrenom_user(String prenom_user) {
        this.prenom_user = prenom_user;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }
}
