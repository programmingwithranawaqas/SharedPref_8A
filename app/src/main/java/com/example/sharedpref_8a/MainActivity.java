package com.example.sharedpref_8a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    FragmentManager manager;
    View loginView, signupView;

    Button btnLogin, btnRLogin, btnSignup, btnRSignup;
    TextInputEditText etUsername, etPassword, etRUsername, etRPassword, etRConfirmPassword;

    Fragment LoginFragment, SignUpFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        SharedPreferences sPref = getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        SharedPreferences.Editor editor1 = sPref.edit();


        boolean flag = sPref.getBoolean("isLogin", false);
        if(flag)
        {
            Intent intent = new Intent(MainActivity.this,
                    Home.class);
            startActivity(intent);
            finish();
        }

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.beginTransaction()
                        .hide(LoginFragment)
                        .show(SignUpFragment)
                        .commit();
            }
        });

        btnRLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.beginTransaction()
                        .show(LoginFragment)
                        .hide(SignUpFragment)
                        .commit();
            }
        });

        btnRSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("username", Objects.requireNonNull(etRUsername.getText()).toString());
                editor.putString("password", Objects.requireNonNull(etRPassword.getText()).toString());
                editor.apply();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = sPref.getString("username", null);
                String passwrod = sPref.getString("password", null);
                if (username != null && passwrod!=null)
                {
                    if(etUsername.getText().toString().equals(username)
                    && etPassword.getText().toString().equals(passwrod))
                    {
                        editor1.putBoolean("isLogin", true);
                        editor1.apply();

                        Intent intent = new Intent(MainActivity.this,
                                Home.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

    }

    private void init()
    {
        manager = getSupportFragmentManager();
        loginView = Objects.requireNonNull(manager.findFragmentById(R.id.fragLogin)).requireView();
        signupView = Objects.requireNonNull(manager.findFragmentById(R.id.fragSignup)).requireView();
        btnLogin = loginView.findViewById(R.id.btnLogin);
        btnSignup = loginView.findViewById(R.id.btnSignup);
        btnRLogin = signupView.findViewById(R.id.btnRLogin);
        btnRSignup = signupView.findViewById(R.id.btnRSignup);

        etUsername = loginView.findViewById(R.id.etUsername);
        etPassword = loginView.findViewById(R.id.etPassword);
        etRUsername = signupView.findViewById(R.id.etRUsername);
        etRPassword = signupView.findViewById(R.id.etRPassword);
        etRConfirmPassword = signupView.findViewById(R.id.etRConfirmPassword);

        LoginFragment = manager.findFragmentById(R.id.fragLogin);
        SignUpFragment = manager.findFragmentById(R.id.fragSignup);

    }
}