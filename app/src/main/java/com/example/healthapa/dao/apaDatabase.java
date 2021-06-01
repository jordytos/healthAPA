package com.example.healthapa.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.healthapa.entities.Utilisateur;

@Database(entities = {Utilisateur.class}, version = 2)
public abstract class apaDatabase extends RoomDatabase {

    public abstract UtilisateurDao utilisateurDao();

    private static volatile apaDatabase INSTANCE;

    public static apaDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (apaDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            apaDatabase.class, "health_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
