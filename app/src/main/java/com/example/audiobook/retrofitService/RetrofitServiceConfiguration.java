package com.example.audiobook.retrofitService;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceConfiguration {
    private Retrofit retrofit;

    public RetrofitServiceConfiguration() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        retrofit= new Retrofit.Builder()
                .baseUrl("http://192.168.1.144:9999")
                .addConverterFactory(GsonConverterFactory.create(new Gson())).build();
    }
}
