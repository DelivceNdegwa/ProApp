package com.example.proapp;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProDetailsActivity extends AppCompatActivity {
    TextView txtName, txtOccupation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_details);

        txtName = findViewById(R.id.txt_name);
        txtOccupation = findViewById(R.id.txt_occupation);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(getIntent().hasExtra("NAME")){
            txtName.setText(getIntent().getStringExtra("NAME"));
        }
        if(getIntent().hasExtra("OCCUPATION")){
            txtOccupation.setText(getIntent().getStringExtra("OCCUPATION"));
        }
    }
}

