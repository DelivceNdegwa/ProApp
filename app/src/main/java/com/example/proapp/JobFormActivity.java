package com.example.proapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proapp.adapters.JobProffesionAdapter;
import com.example.proapp.models.Job;
import com.example.proapp.models.Profession;
import com.example.proapp.utils.ObjectBox;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

public class JobFormActivity extends AppCompatActivity {
    Button btnPublish;
    ImageView imgAttachment;
    RecyclerView rvProfession;

    TextInputEditText inputSalary, inputMode, descriptionId, jobTitle, locationId,
    inputRequirements, inputWorkingHours;

    List<Profession> professions = new ArrayList<>();
    Box<Profession> professionBox = ObjectBox.get().boxFor(Profession.class);
    Box<Job> jobBox = ObjectBox.get().boxFor(Job.class);

    JobProffesionAdapter jobProfessionAdapter;

    final static int DOCUMENT_CODE = 177;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_form);

        inputSalary = findViewById(R.id.id_salary);
        inputMode = findViewById(R.id.id_mode);
        descriptionId = findViewById(R.id.description_id);
        jobTitle = findViewById(R.id.job_title);
        locationId = findViewById(R.id.location_id);
        inputRequirements = findViewById(R.id.requirements_id);
        inputWorkingHours = findViewById(R.id.id_hours);

        btnPublish = findViewById(R.id.btn_post);
        imgAttachment = findViewById(R.id.img_attachment);
        rvProfession = findViewById(R.id.rv_profession);

        rvProfession.setNestedScrollingEnabled(true);
        rvProfession.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        professions.addAll(professionBox.getAll());
        jobProfessionAdapter = new JobProffesionAdapter(this, professions);
        rvProfession.setAdapter(jobProfessionAdapter);

        imgAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickDoc();
            }
        });

        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateInput() && pickDoc()){
                    saveJobs();

                }
            }
        });


    }

    private void saveJobs() {

        Job job = new Job();
        job.setDescription(descriptionId.getText().toString().trim());
        job.setRequirements(inputRequirements.getText().toString().trim());
        job.setTitle(jobTitle.getText().toString().trim());
        job.setExpiry_date("Later");
        job.setLocation(locationId.getText().toString().trim());
        job.setSalary(Integer.parseInt(inputSalary.getText().toString().trim()));
        job.setWorking_hours(Integer.parseInt(inputWorkingHours.getText().toString().trim()));
        job.setProfession(jobProfessionAdapter.profession_id);
        long id = jobBox.put(job);

        Intent intent = new Intent(JobFormActivity.this, JobDetailsActivity.class);
        intent.putExtra("JOB_ID", id);
        startActivity(intent);
    }


    public boolean validateInput(){
        boolean response = false;
        if(inputSalary.getText().toString().trim().isEmpty()){
            inputSalary.setError("Please enter a salary");
        }
        else if(inputMode.getText().toString().trim().isEmpty()){
            inputMode.setError("Please enter a mode");
        }

        else if(inputRequirements.getText().toString().trim().isEmpty()){
            inputRequirements.setError("Please enter a requirement");
        }
        else if(descriptionId.getText().toString().trim().isEmpty()){
            descriptionId.setError("Please input a description");
        }
        else if(jobTitle.getText().toString().trim().isEmpty()){
            jobTitle.setError("Please input a job title");
        }
        else if(locationId.getText().toString().trim().isEmpty()){
            locationId.setError("Please input job location");
        }

        else if(inputWorkingHours.getText().toString().trim().isEmpty()){
            inputWorkingHours.setError("Please input working hours");
        }
        else if(jobProfessionAdapter.profession_id == 0){
            Toast.makeText(this, "Please enter a profession", Toast.LENGTH_SHORT).show();
        }
        else{
            response = true;
        }
        return response;
    }


    public boolean pickDoc(){
        boolean response = true;

        Intent pickDocument = new Intent(Intent.ACTION_GET_CONTENT);
        pickDocument.setType("application/*");
        pickDocument.addCategory(Intent.CATEGORY_OPENABLE);

        String[] mimeTypes = {
                "application/pdf",
                "application/xls",
                "application/docx",
                "application/doc",
        };

        pickDocument.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        try{
            startActivityForResult(pickDocument, DOCUMENT_CODE);

        }
        catch (ActivityNotFoundException e){
            Toast.makeText(this, "No application found to open files", Toast.LENGTH_SHORT).show();
            response = false;
        }

        return response;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == DOCUMENT_CODE && resultCode == RESULT_OK){
            Uri uri = data.getData();
            imgAttachment.setImageDrawable(getResources().getDrawable(R.drawable.ic_success_doc));
        }

    }
}