package com.ot.jgomez.recepcion.api;

import com.ot.jgomez.recepcion.control.Constantes;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jgomez on 9/02/17.
 */

public class MyService {
    private static MyService mInstance = new MyService();
    private serverAPI mApi;

    private MyService() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder =
                new Retrofit.Builder()
                    .baseUrl(Constantes.API_URL)
                    .addConverterFactory(GsonConverterFactory.create());
        OkHttpClient.Builder client = httpClient;
        client.connectTimeout(15, TimeUnit.SECONDS);
        client.readTimeout(15, TimeUnit.SECONDS);
        Retrofit retrofit = builder.client(client.build()).build();

        this.mApi = retrofit.create(serverAPI.class);
    }
}
