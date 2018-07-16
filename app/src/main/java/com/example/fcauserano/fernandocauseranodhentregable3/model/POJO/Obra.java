package com.example.fcauserano.fernandocauseranodhentregable3.model.POJO;

import java.io.Serializable;

public class Obra implements Serializable {

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
