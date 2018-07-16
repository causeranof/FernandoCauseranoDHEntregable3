package com.example.fcauserano.fernandocauseranodhentregable3;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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
