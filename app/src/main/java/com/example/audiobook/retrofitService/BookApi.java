package com.example.audiobook.retrofitService;

import com.example.audiobook.dto.BookDetailDto;
import com.example.audiobook.entity.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface BookApi {
   @GET()
   Class<List<Book>> getAllBook();

   @GET("/book/{id}")
   Call<BookDetailDto> getBookById(@Path("id") Long id);
}
