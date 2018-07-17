package com.example.fcauserano.fernandocauseranodhentregable3.model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.fcauserano.fernandocauseranodhentregable3.model.POJO.Artista;
import com.example.fcauserano.fernandocauseranodhentregable3.model.POJO.Obra;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface ArtistaRoomDAO {

    @Query("select * from artista")
    List<Obra> loadAllArtistas();

    @Query("select * from artista where artistId = :artistId")
    Artista loadArtistaById(String artistId);

    @Insert(onConflict = IGNORE)
    void insertObra(Artista artista);

    @Query("delete from artista")
    void deleteAllArtistas();
    
    @Insert
    void insertAllObras(Obra... obras);
}
