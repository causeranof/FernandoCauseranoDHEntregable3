package com.example.fcauserano.fernandocauseranodhentregable3.model.POJO;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Artista {

    @PrimaryKey
    @NonNull
    private String artistId;
    private String name;
    private String nationality;
    private String influenced_by;

    @Ignore
    public Artista() {
    }

    public Artista(@NonNull String artistId, String name, String nationality, String influenced_by) {
        this.artistId = artistId;
        this.name = name;
        this.nationality = nationality;
        this.influenced_by = influenced_by;
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
        return influenced_by;
    }
    public void setArtistId(@NonNull String artistId) {
        this.artistId = artistId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public void setInfluenced_by(String influenced_by) {
        this.influenced_by = influenced_by;
    }
}
