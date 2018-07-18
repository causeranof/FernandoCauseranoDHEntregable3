package com.example.fcauserano.fernandocauseranodhentregable3.model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.fcauserano.fernandocauseranodhentregable3.model.POJO.Artista;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ArtistaRoomDAO {

    @Query("select * from artista")
    List<Artista> loadAllArtistas();

    @Query("select * from artista where artistId = :artistId")
    Artista loadArtistaById(String artistId);

    @Insert(onConflict = REPLACE)
    void insertAllArtistas(List<Artista> artistas);

    @Query("delete from artista")
    void deleteAllArtistas();
}
