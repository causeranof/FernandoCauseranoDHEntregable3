package com.example.fcauserano.fernandocauseranodhentregable3.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.fcauserano.fernandocauseranodhentregable3.model.POJO.Obra;
import com.example.fcauserano.fernandocauseranodhentregable3.model.DAO.ObraDAO;
import com.example.fcauserano.fernandocauseranodhentregable3.utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class ObraController {
    private Context context;

    public ObraController(Context context) {
        this.context = context;
    }

    public void obtenerObras(final ResultListener<List<Obra>> resultListener) {
        if (hayInternet()) {
            ObraDAO obraDAO = new ObraDAO();
            obraDAO.obtenerObras(new ResultListener<List<Obra>>() {
                @Override
                public void finish(List<Obra> listadoObras) {
                    resultListener.finish(listadoObras);
                }
            });
        } else {
            resultListener.finish(new ArrayList<Obra>());
        }
    }

    public boolean hayInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }
}
