package com.example.fcauserano.fernandocauseranodhentregable3.model.DAO;

import android.content.Context;
import android.os.AsyncTask;

import com.example.fcauserano.fernandocauseranodhentregable3.model.POJO.Obra;
import com.example.fcauserano.fernandocauseranodhentregable3.utils.ResultListener;

import java.util.List;

public class ObraRoomDaoUtil {

    private AppDatabase database;

    public ObraRoomDaoUtil(Context context) {
        this.database = AppDatabase.getDatabaseInstance(context);
    }

    public void insertAllObras(List<Obra> obras){
        InsertAllObrasAsync insertAllObrasAsync = new InsertAllObrasAsync(obras);
        insertAllObrasAsync.execute();
    }

    public class InsertAllObrasAsync extends AsyncTask<Void, Void, Void>{

        private List<Obra> obras;

        public InsertAllObrasAsync(List<Obra> obras) {
            this.obras = obras;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.obraRoomDAO().insertAllObras(obras);
            return null;
        }
    }

    public void loadAllObras(ResultListener<List<Obra>> resultListener) {
        LoadAllObrasAsync loadAllObrasAsync = new LoadAllObrasAsync(resultListener);
        loadAllObrasAsync.execute();
    }

    private class LoadAllObrasAsync extends AsyncTask<Void, Void, List<Obra>> {

        private ResultListener<List<Obra>> resultListener;

        public LoadAllObrasAsync(ResultListener<List<Obra>> resultListener) {
            this.resultListener = resultListener;
        }

        @Override
        protected List<Obra> doInBackground(Void... voids) {
            return database.obraRoomDAO().loadAllObras();
        }

        @Override
        protected void onPostExecute(List<Obra> obras) {
            resultListener.finish(obras);
        }
    }

}
