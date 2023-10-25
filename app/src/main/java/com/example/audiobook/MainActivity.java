package com.example.audiobook;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wellcome);

        Button buttonLogin = findViewById(R.id.showLoginForm);
        Button buttonSignUp = findViewById(R.id.showSignUpForm);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate the login form layout
                View bottomSheetView = getLayoutInflater().inflate(R.layout.login_form, null);

                // Create a BottomSheetDialog to show the login form
                BottomSheetDialog dialog = new BottomSheetDialog(MainActivity.this);
                dialog.setContentView(bottomSheetView);
                dialog.show();
            }
        });
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate the login form layout
                View bottomSheetView = getLayoutInflater().inflate(R.layout.login_form, null);

                // Create a BottomSheetDialog to show the login form
                BottomSheetDialog dialog = new BottomSheetDialog(MainActivity.this);
                dialog.setContentView(bottomSheetView);
                dialog.show();
            }
        });


    }
}
