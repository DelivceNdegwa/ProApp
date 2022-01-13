package com.example.proapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proapp.utils.PreferenceStorage;

public class MainActivity extends AppCompatActivity {

    int count = 0;
    Button btnCreateProf, btnMain;
    PreferenceStorage preferenceStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferenceStorage = new PreferenceStorage(this);

        btnMain = findViewById(R.id.btn_main);
        btnCreateProf = findViewById(R.id.btn_create_prof);


        if(preferenceStorage.isLoggedIn()){
            btnCreateProf.setVisibility(View.GONE);
        }


        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHome = new Intent(MainActivity.this, ExploreActivity.class);
                startActivity(goToHome);
                finish();
            }
        });
        btnCreateProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAuth = new Intent(MainActivity.this, JobFormActivity.class);
                startActivity(goToAuth);
            }
        });
    }
}