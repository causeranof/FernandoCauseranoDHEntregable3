package com.example.fcauserano.fernandocauseranodhentregable3.model.DAO;

import android.content.Context;
import android.os.AsyncTask;

import com.example.fcauserano.fernandocauseranodhentregable3.model.POJO.Artista;
import com.example.fcauserano.fernandocauseranodhentregable3.utils.ResultListener;

import java.util.List;


public class ArtistaRoomDaoUtil {

    private AppDatabase database;

    public ArtistaRoomDaoUtil(Context context) {
        this.database = AppDatabase.getDatabaseInstance(context);
    }

    public void loadAllArtistas (ResultListener<List<Artista>> artistaListener){
        LoadAllArtistasAsync loadAllArtistas = new LoadAllArtistasAsync(artistaListener);
        loadAllArtistas.execute();
    }
    public void loadArtistaById(String idArtista, ResultListener<Artista> artistaResultListener) {
        LoadArtistaByIdAsync loadArtistaByIdAsync = new LoadArtistaByIdAsync(idArtista, artistaResultListener);
        loadArtistaByIdAsync.execute();
    }
    public void insertAllArtistas(List<Artista> artistas){
        InsertAllArtistasAsync insertAllArtistasAsync = new InsertAllArtistasAsync(artistas);
        insertAllArtistasAsync.execute();

    }

    public class InsertAllArtistasAsync extends AsyncTask<Void, Void, Void>{
        private List<Artista> artistas;

        public InsertAllArtistasAsync(List<Artista> artistas) {
            this.artistas = artistas;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.artistaRoomDAO().insertAllArtistas(artistas);
            return null;
        }
    }

    private class LoadArtistaByIdAsync extends AsyncTask<Void, Void, Artista> {
        private ResultListener<Artista> artistaResultListener;
        private String idArtista;

        public LoadArtistaByIdAsync(String idArtista, ResultListener<Artista> artistaResultListener) {
            this.artistaResultListener = artistaResultListener;
            this.idArtista = idArtista;
        }

        @Override
        protected Artista doInBackground(Void... voids) {
            return database.artistaRoomDAO().loadArtistaById(idArtista);
        }

        @Override
        protected void onPostExecute(Artista artista) {
            artistaResultListener.finish(artista);
        }
    }

    private class LoadAllArtistasAsync extends AsyncTask<Void, Void, List<Artista>>{
        private ResultListener<List<Artista>> artistaListener;
        public LoadAllArtistasAsync(ResultListener<List<Artista>> artistaListener) {
            this.artistaListener=artistaListener;
        }

        @Override
        protected List<Artista> doInBackground(Void... voids) {
            return database.artistaRoomDAO().loadAllArtistas();
        }

        @Override
        protected void onPostExecute(List<Artista> artistas) {
            artistaListener.finish(artistas);
        }
    }
}
