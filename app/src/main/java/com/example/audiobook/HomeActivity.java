package com.example.audiobook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.audiobook.entity.User;
import com.google.gson.Gson;

public class HomeActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
//
//        pref = getApplicationContext().getSharedPreferences("Account", Context.MODE_PRIVATE);
//        editor = pref.edit();
//        Gson gson= new Gson();
//        User user = gson.fromJson(pref.getString("user",null), User.class);
//        if (user!=null){
//            TextView tx= findViewById(R.id.tv_username);
//            tx.setText(user.getName());
//        }
    }
}