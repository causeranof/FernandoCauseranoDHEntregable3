package com.example.fcauserano.fernandocauseranodhentregable3.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.fcauserano.fernandocauseranodhentregable3.model.DAO.AppDatabase;
import com.example.fcauserano.fernandocauseranodhentregable3.model.DAO.ArtistaDAO;
import com.example.fcauserano.fernandocauseranodhentregable3.model.POJO.Artista;
import com.example.fcauserano.fernandocauseranodhentregable3.utils.ResultListener;

public class ArtistaController {
    private Context context;
    private AppDatabase appDatabase;

    public ArtistaController(Context context) {
        this.context = context;
        appDatabase = AppDatabase.getDatabaseInstance(context);
    }

    public void obtenerArtistaPorId(String idArtista, final ResultListener<Artista> artistaResultListener) {
        if (hayInternet()) {
            ArtistaDAO artistaDAO = new ArtistaDAO();
            artistaDAO.obtenerArtistaPorID(idArtista, new ResultListener<Artista>() {
                @Override
                public void finish(Artista artista) {
                    artistaResultListener.finish(artista);
                }
            });
        } else {
            artistaResultListener.finish(new Artista());
        }
    }

    public boolean hayInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }
}
