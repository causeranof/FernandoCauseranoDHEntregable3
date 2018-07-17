package com.example.fcauserano.fernandocauseranodhentregable3.model.POJO;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Artista {

    @PrimaryKey
    @NonNull
    private String artistId;
    private String name;
    private String nationality;
    private String Influenced_by;

    public Artista() {
    }

    public String getArtistId() {
        return artistId;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public String getInfluenced_by() {
        return Influenced_by;
    }
}
