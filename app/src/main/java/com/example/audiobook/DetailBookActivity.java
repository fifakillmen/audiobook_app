package com.example.audiobook;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.audiobook.dto.BookDetailDto;
import com.example.audiobook.retrofitService.BookApi;
import com.example.audiobook.retrofitService.RetrofitServiceConfiguration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);

        ImageView image = findViewById(R.id.imageView);
        TextView textBookName = findViewById(R.id.textView2);

        RetrofitServiceConfiguration retrofitServiceConfiguration = new RetrofitServiceConfiguration();

        Retrofit retrofit = retrofitServiceConfiguration.getRetrofit();
        BookApi bookApi = retrofit.create(BookApi.class);

        Call<BookDetailDto> call = bookApi.getBookById(1L);
//        call.enqueue(new Callback<BookDetailDto>() {
//            @Override
//            public void onResponse(Call<BookDetailDto> call, Response<BookDetailDto> response) {
//                BookDetailDto book = response.body();
//
//                Log.d("bookname", book.getName());
//                Log.d("abc", "testttttt");
//                textBookName.setText(book.getName());
//            }
//
//            @Override
//            public void onFailure(Call<BookDetailDto> call, Throwable t) {
//                Log.d("fail", "failllll");
//            }
//        });
        bookApi.getBookById(1L).enqueue(new Callback<BookDetailDto>() {
            @Override
            public void onResponse(Call<BookDetailDto> call, Response<BookDetailDto> response) {
                BookDetailDto book = response.body();

                Log.d("bookname", book.getName());
                Log.d("abc", "testttttt");
                textBookName.setText(book.getName());
            }

            @Override
            public void onFailure(Call<BookDetailDto> call, Throwable t) {
                Log.d("fail", "failllll");
            }
        });
    }
}
