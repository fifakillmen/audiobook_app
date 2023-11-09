package com.example.audiobook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import com.example.audiobook.dto.AccountRequestDto;
import com.example.audiobook.dto.AccountResponseDto;
import com.example.audiobook.entity.Account;
import com.example.audiobook.retrofitService.AccountApi;
import com.example.audiobook.retrofitService.AuthApi;
import com.example.audiobook.retrofitService.RetrofitServiceConfiguration;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private BottomSheetDialog loginDialog;
    private BottomSheetDialog signupDialog;
    private AuthApi authApiService;
    private AccountApi accountApi;

    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wellcome);
        gson = new Gson();


        //
        loginDialog = new BottomSheetDialog(this);
        signupDialog= new BottomSheetDialog(this);
        //create retro
        RetrofitServiceConfiguration retrofitServiceConfiguration = new RetrofitServiceConfiguration();
        authApiService = retrofitServiceConfiguration.getRetrofit().create(AuthApi.class);
        accountApi = retrofitServiceConfiguration.getRetrofit().create(AccountApi.class);


        pref = getApplicationContext().getSharedPreferences("Account", Context.MODE_PRIVATE);
        editor = pref.edit();

        //
        Button buttonShowLogin = findViewById(R.id.showLoginForm);
        Button buttonShowSignUp = findViewById(R.id.showSignUpForm);

        View bottomSheetView_Login = getLayoutInflater().inflate(R.layout.login_form, null);
        View bottomSheetView_SignUp = getLayoutInflater().inflate(R.layout.signup_form, null);

        Button buttonLogin = bottomSheetView_Login.findViewById(R.id.btnLogin);
        Button buttonSignUp = bottomSheetView_SignUp.findViewById(R.id.btnSignUp);

        setupShowLoginForm(buttonShowLogin, bottomSheetView_Login);
        setupShowSignUpForm(buttonShowSignUp, bottomSheetView_SignUp);

        setupLoginButton(buttonLogin, bottomSheetView_Login);
        setupSignUpButton(buttonSignUp, bottomSheetView_SignUp);



        // lay data tu pref de check
        String email = pref.getString("email", null);
        String password = pref.getString("password", null);
        Boolean remember = pref.getBoolean("remember", false);
        String accessToken = pref.getString("accessToken", null);
        Boolean isLogin = pref.getBoolean("isLogin", false);
        // neu luu thi check token con song ko
        if (remember) {
            EditText loginEmail = bottomSheetView_Login.findViewById(R.id.login_email);
            EditText loginPassword = bottomSheetView_Login.findViewById(R.id.login_password);
            loginEmail.setText(email);
            loginPassword.setText(password);
            authApiService.checkAccessToken(accessToken).enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if (Boolean.TRUE.equals(response.body())) {
                            editor.putBoolean("isLogin",true);
                            editor.apply();
                            editor.commit();

                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Out of permission time!!", Toast.LENGTH_SHORT).show();
                        }
                }
                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Cannot connect to sever!!!!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private void setupShowSignUpForm(Button buttonShowSignUp, View bottomSheetView_SignUp) {
        buttonShowSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginDialog.isShowing()) {
                    loginDialog.dismiss();
                }
                TextView loginTextView = bottomSheetView_SignUp.findViewById(R.id.tvAlreadyAcc);
                SpannableString spannableString = new SpannableString("Already have an account? Login");

                ClickableSpan clickableSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        if (loginDialog.isShowing()) {
                            loginDialog.dismiss();
                        }
                        loginDialog.show();
                    }
                };

                spannableString.setSpan(clickableSpan, spannableString.length() - "Login".length(), spannableString.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

                loginTextView.setText(spannableString);
                loginTextView.setMovementMethod(LinkMovementMethod.getInstance());

                signupDialog.setContentView(bottomSheetView_SignUp);
                signupDialog.show();
            }
        });
    }

    private void setupShowLoginForm(Button buttonShowLogin, View bottomSheetView_Login) {
        buttonShowLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginDialog.isShowing()) {
                    loginDialog.dismiss();
                }

                if (pref.getBoolean("remember", false)) {
                    EditText loginEmail = bottomSheetView_Login.findViewById(R.id.login_email);
                    EditText loginPassword = bottomSheetView_Login.findViewById(R.id.login_password);
                    loginEmail.setText(pref.getString("email", null));
                    loginPassword.setText(pref.getString("password", null));
                }

                loginDialog.setContentView(bottomSheetView_Login);
                loginDialog.show();
            }
        });
    }

    private void setupLoginButton(Button buttonLogin, View bottomSheetView_Login) {

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText loginEmail = bottomSheetView_Login.findViewById(R.id.login_email);
                EditText loginPassword = bottomSheetView_Login.findViewById(R.id.login_password);
                CheckBox checkedRem = bottomSheetView_Login.findViewById(R.id.cekRememberAccount);

                // Handle login or other actions here
                AccountRequestDto accountRequestDto= new AccountRequestDto();
                accountRequestDto.setEmail(String.valueOf(loginEmail.getText()));
                accountRequestDto.setPassword(String.valueOf(loginPassword.getText()));
                authApiService.login(accountRequestDto).enqueue(new Callback<AccountResponseDto>() {
                    @Override
                    public void onResponse(Call<AccountResponseDto> call, Response<AccountResponseDto> response) {

                        AccountResponseDto responseDto= response.body();
                        if(responseDto == null || responseDto.getAccessToken().isEmpty()){
                            Toast.makeText(MainActivity.this, "Login fail!!", Toast.LENGTH_SHORT).show();
                        }else {
                            if (checkedRem.isChecked()) {
                                editor.putString("email", String.valueOf(loginEmail.getText()));
                                editor.putString("password", String.valueOf(loginPassword.getText()));
                                editor.putBoolean("remember", true);
                                editor.putString("accessToken",responseDto.getAccessToken());
                                editor.putString("roles",gson.toJson(responseDto.getRoles()));
                                if (responseDto.getUser()!=null){
                                    Log.w("user",responseDto.getUser().toString());
                                    editor.putString("user",gson.toJson(responseDto.getUser()));
                                }
                            } else {
                                editor.clear();
                            }
                            editor.apply();
                            editor.commit();
                            if (responseDto.getUser()!=null){
                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }else {
                                Intent intent = new Intent(MainActivity.this, CreateUserActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<AccountResponseDto> call, Throwable t) {
                        Log.w("loginEmail",loginEmail.getText().toString());
                        Log.w("loginEmail",loginPassword.getText().toString());
                        Log.w("errorr",t.toString());
                        Toast.makeText(MainActivity.this, "Cannot connect to sever!!!!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
    private void setupSignUpButton(Button buttonSignUp, View bottomSheetView_SignUp) {
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText signUp_email = bottomSheetView_SignUp.findViewById(R.id.tx_signUp_email);
                EditText signUp_password = bottomSheetView_SignUp.findViewById(R.id.tx_signUp_password);
                EditText signUp_re_password = bottomSheetView_SignUp.findViewById(R.id.tx_signUp_re_password);

                if (String.valueOf(signUp_password.getText()).equals(String.valueOf(signUp_re_password.getText()))){
                    // Handle signUp
                    accountApi.signUp(String.valueOf(signUp_email.getText()),
                            String.valueOf(signUp_password.getText()),
                            String.valueOf(signUp_re_password.getText())).enqueue(new Callback<Account>() {
                        @Override
                        public void onResponse(Call<Account> call, Response<Account> response) {
                            Account account=response.body();
                            if (account!=null){
                                //thong bao tao account thanh cong
                                Toast.makeText(MainActivity.this, "SignUp successfully,you can login now!!", Toast.LENGTH_SHORT).show();
                                // dong lai bottom sheet
                                if (signupDialog.isShowing()) {
                                    signupDialog.dismiss();
                                }
                            }else {
                                Toast.makeText(MainActivity.this, "SignUp fail!!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Account> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "SignUp fail!!!", Toast.LENGTH_SHORT).show();

                        }
                    });
                }else {
                    Toast.makeText(MainActivity.this, "Password and repeat password must be same", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
