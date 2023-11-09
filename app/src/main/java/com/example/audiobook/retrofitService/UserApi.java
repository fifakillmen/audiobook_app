package com.example.audiobook.retrofitService;

import com.example.audiobook.entity.User;
import com.example.audiobook.enumPack.Enum_Gender;

import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApi {
    @POST("/user/addUser")
    Call<User> createUser(@Query("name") String name,
                          @Query("dob") LocalDate dob,
                          @Query("address")String address,
                          @Query("gender")Enum_Gender gender,
                          @Query("email")String email);
}
