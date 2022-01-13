package com.example.proapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.proapp.databinding.ActivityExploreBinding;
import com.example.proapp.models.Job;
import com.example.proapp.utils.ObjectBox;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class JobDetailsActivity extends AppCompatActivity {

    private ActivityExploreBinding binding;

    TextView tvDescription,tvRequirements, expiryDate;
    Chip chipSalary, chipLocation;
    CollapsingToolbarLayout toolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_job_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvDescription = findViewById(R.id.tv_description);
        tvRequirements = findViewById(R.id.tv_requirements);
        expiryDate = findViewById(R.id.expiry_date);
        chipSalary = findViewById(R.id.chip_salary);
        chipLocation = findViewById(R.id.chip_location);

        Intent intent = getIntent();
        if(intent.hasExtra("JOB_ID")){
            Job jobForm = ObjectBox.get().boxFor(Job.class).get(intent.getLongExtra("JOB_ID", 0));
            tvDescription.setText(jobForm.getDescription());
            tvRequirements.setText(jobForm.getRequirements());
            expiryDate.setText(jobForm.getExpiry_date());
            chipSalary.setText(String.valueOf(jobForm.getSalary()));
            chipLocation.setText(jobForm.getLocation());
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_job_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menu_item = item.getItemId();
        switch (menu_item){
            case R.id.action_settings:
                Toast.makeText(JobDetailsActivity.this, "You reached settings.", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_favorite:
                Toast.makeText(JobDetailsActivity.this, "I like you too.", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_mail:
                Intent sendMail = new Intent(Intent.ACTION_SEND);
                sendMail.putExtra(Intent.EXTRA_SUBJECT,"I need a job");
                sendMail.setType("message/rfc822");
                String[] addresses = {"randomemail@gmail.com"};
                sendMail.putExtra(Intent.EXTRA_EMAIL, addresses);
                startActivity(sendMail);


                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}