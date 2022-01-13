package com.example.proapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.proapp.models.Job;
import com.example.proapp.ui.home.HomeViewModel;
import com.example.proapp.utils.ObjectBox;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.chip.Chip;


public class JobDetailsFragment extends Fragment {
    
    public JobDetailsFragment() {
        // Required empty public constructor
    }


    TextView tvDescription,tvRequirements, expiryDate;
    Chip chipSalary, chipLocation;
    CollapsingToolbarLayout toolbarLayout;
    HomeViewModel homeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        View root = inflater.inflate(R.layout.fragment_job_details, container, false);

        tvDescription = root.findViewById(R.id.tv_description);
        tvRequirements = root.findViewById(R.id.tv_requirements);
        expiryDate = root.findViewById(R.id.expiry_date);
        chipSalary = root.findViewById(R.id.chip_salary);
        chipLocation = root.findViewById(R.id.chip_location);

        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

//        if( getArguments().containsKey("ID")){
//
//        }

      homeViewModel.getSelected().observe(requireActivity(), id -> {
          displayData(id);
      });
    }

    public void displayData(long id){
        Job jobForm = ObjectBox.get().boxFor(Job.class).get(id);
        tvDescription.setText(jobForm.getDescription());
        tvRequirements.setText(jobForm.getRequirements());
        expiryDate.setText(jobForm.getExpiry_date());
        chipSalary.setText(String.valueOf(jobForm.getSalary()));
        chipLocation.setText(jobForm.getLocation());

    }
}