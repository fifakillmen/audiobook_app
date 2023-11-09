package com.example.audiobook.retrofitService;

import com.example.audiobook.config.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceConfiguration {
    private Retrofit retrofit;


    public RetrofitServiceConfiguration() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        retrofit= new Retrofit.Builder()
                .baseUrl("http://10.33.22.22:9999")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
