package com.example.audiobook.retrofitService;

import com.example.audiobook.entity.Book;

import java.util.List;

import retrofit2.http.GET;

public interface BookApi {
   @GET()
   Class<List<Book>> getAllBook();
}