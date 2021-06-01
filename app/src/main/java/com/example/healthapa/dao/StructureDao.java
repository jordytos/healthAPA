package com.example.healthapa.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.healthapa.entities.Structure;

import java.util.List;

@Dao
public interface StructureDao {

    @Insert
    void insererStructure(Structure structure);

    @Query("SELECT * FROM Structure")
    List<Structure> findAllStructure();

    @Query("SELECT nom FROM Structure")
    List<String> findByNameAllStructure();

}
