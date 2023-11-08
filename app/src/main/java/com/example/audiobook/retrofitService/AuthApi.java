package com.example.audiobook.retrofitService;

import com.example.audiobook.dto.AccountRequestDto;
import com.example.audiobook.dto.AccountResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthApi {
    @Headers("Content-Type: application/json")
    @POST("/login")
    Call<AccountResponseDto> login(@Body AccountRequestDto accountRequestDto);

    @POST("/checkAccessToken")
    Call<Boolean> checkAccessToken(@Query("accessToken") String accessToken);
}
