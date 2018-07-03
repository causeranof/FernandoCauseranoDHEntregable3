package com.example.fcauserano.fernandocauseranodhentregable3;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ObraDAO {

    private static final String URL = "https://api.myjson.com/bins/";
    private Retrofit retrofit;
    private ObraService obraService;

    public ObraDAO() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(URL);
        builder.addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
        obraService = retrofit.create(ObraService.class);
    }

    public void obtenerObras(final ResultListener<List<Obra>> resultListener) {
        obraService.obtenerObras().enqueue(new Callback<ObrasContainer>() {
            @Override
            public void onResponse(Call<ObrasContainer> call, Response<ObrasContainer> response) {
                ObrasContainer obrasContainer = response.body();
                if (obrasContainer != null && obrasContainer.getObras() != null) {
                    List<Obra> listaObras = obrasContainer.getObras();
                    resultListener.finish(listaObras);
                } else {
                    resultListener.finish(new ArrayList<Obra>());
                }
            }

            @Override
            public void onFailure(Call<ObrasContainer> call, Throwable t) {
                resultListener.finish(new ArrayList<Obra>());
            }
        });
    }
}
