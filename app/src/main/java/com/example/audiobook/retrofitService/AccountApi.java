package com.example.audiobook.retrofitService;

import com.example.audiobook.entity.Account;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AccountApi {
    @POST("/account/signUp")
    Call<Account> signUp(@Query("email") String email,
                         @Query("password") String password,
                         @Query("re_password") String re_password);

}
