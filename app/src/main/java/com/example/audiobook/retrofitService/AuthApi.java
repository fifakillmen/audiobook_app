package com.example.audiobook.retrofitService;

import com.example.audiobook.dto.AccountRequestDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("/login")
    Call<String> login(@Body AccountRequestDto accountRequestDto);
}
