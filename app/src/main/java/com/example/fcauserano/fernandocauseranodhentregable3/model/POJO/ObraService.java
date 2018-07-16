package com.example.fcauserano.fernandocauseranodhentregable3.model.POJO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ObraService {

    @GET ("x858r")
    Call<ObrasContainer> obtenerObras();
}
