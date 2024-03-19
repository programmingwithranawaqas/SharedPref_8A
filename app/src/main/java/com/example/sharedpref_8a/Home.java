package com.example.sharedpref_8a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    TextView tvUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvUsername =  findViewById(R.id.tvUsername);

        SharedPreferences sPref = getSharedPreferences("Login", MODE_PRIVATE);
        String username = sPref.getString("username", "");
        tvUsername.setText(username);

    }
}