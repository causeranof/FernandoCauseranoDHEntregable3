package com.example.fcauserano.fernandocauseranodhentregable3.model.POJO;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(foreignKeys = {
        @ForeignKey(entity = Artista.class,
        parentColumns = "artistId",
        childColumns = "artistId")})
public class Obra implements Serializable {

    @PrimaryKey
    @NonNull
    private String name;
    private String image;

    private String artistId;
    private Artista artista;

    public Obra() {
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getArtistId() {
        return artistId;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }
}
