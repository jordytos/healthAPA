package com.example.healthapa.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.healthapa.entities.Activite;
import com.example.healthapa.entities.Parcours;
import com.example.healthapa.entities.Structure;
import com.example.healthapa.entities.Utilisateur;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Utilisateur.class, Parcours.class, Structure.class, Activite.class}, version = 7)
public abstract class apaDatabase extends RoomDatabase {

    public abstract UtilisateurDao utilisateurDao();
    public abstract ParcoursDao parcoursDao();
    public abstract StructureDao structureDao();
    public abstract ActiviteDao activiteDao();

    private static volatile apaDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

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
