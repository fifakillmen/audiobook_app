package com.example.audiobook.retrofitService;

import com.example.audiobook.dto.BookDetailDto;
import com.example.audiobook.entity.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookApi {

   @GET("books")
   Call<BookDetailDto> searchbook(@Query("page") int page);

}
