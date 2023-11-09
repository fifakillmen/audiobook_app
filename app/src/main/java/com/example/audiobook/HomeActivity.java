package com.example.audiobook;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.audiobook.adapter.BookAdapter;
import com.example.audiobook.entity.Book;
import com.example.audiobook.retrofitService.BookApi;
import com.example.audiobook.retrofitService.RetrofitServiceConfiguration;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        recyclerView = findViewById(R.id.rv_popularbook);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadPopularBooks();
    }

    private void loadPopularBooks() {
        RetrofitServiceConfiguration retrofitServiceConfiguration = new RetrofitServiceConfiguration();
        BookApi bookApi = retrofitServiceConfiguration.getRetrofit().create(BookApi.class);
        bookApi.getAllBook()
                .enqueue(new Callback<List<Book>>() {
                    @Override
                    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                        popularListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Book>> call, Throwable t) {
                        Toast.makeText(HomeActivity.this, "Failed to load book", Toast.LENGTH_SHORT).show();
                    }
                });
        }
        private void popularListView(List<Book> bookList) {
            BookAdapter bookAdapter = new BookAdapter(bookList);
            recyclerView.setAdapter(bookAdapter);
    }
}