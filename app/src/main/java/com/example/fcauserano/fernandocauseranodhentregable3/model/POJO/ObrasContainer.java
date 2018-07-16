package com.example.fcauserano.fernandocauseranodhentregable3.model;

import com.example.fcauserano.fernandocauseranodhentregable3.model.Obra;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ObrasContainer {

    @SerializedName("paints")
    private List<Obra> obras;

    public List<Obra> getObras() {
        return obras;
    }
}
