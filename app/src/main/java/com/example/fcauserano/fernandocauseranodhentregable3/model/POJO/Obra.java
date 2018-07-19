package com.example.fcauserano.fernandocauseranodhentregable3.model.POJO;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity
public class Obra implements Serializable {

    @PrimaryKey
    @NonNull
    private Long obraId;
    private String name;
    private String image;
    private String artistId;
    @Ignore
    private Artista artista;

    @Ignore
    public Obra() {
    }

    public Obra(@NonNull Long obraId, String name, String image, String artistId) {
        this.name = name;
        this.image = image;
        this.artistId = artistId;
        this.obraId = obraId;
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
    @NonNull
    public Long getObraId() {
        return obraId;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }
    public void setName(@NonNull String name) {
        this.name = name;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }
    public void setObraId(@NonNull Long obraId) {
        this.obraId = obraId;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Obra)){
            return false;
        }
        Obra obra = (Obra) obj;
        //return (this.name.equals(obra.getName()));
        return (this.name.equals(obra.getName()) && (this.artistId.equals(obra.getArtistId())));
    }

}
