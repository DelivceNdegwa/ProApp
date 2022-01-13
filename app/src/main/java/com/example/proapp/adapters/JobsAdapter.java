package com.example.proapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proapp.R;
import com.example.proapp.models.Job;
import com.example.proapp.models.Profession;
import com.example.proapp.ui.home.HomeViewModel;
import com.example.proapp.utils.ObjectBox;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.ViewHolder> {

    Context context;
    List<Job> jobs = new ArrayList<>();
    Box<Profession> professionsBox = ObjectBox.get().boxFor(Profession.class);
    HomeViewModel homeViewModel;

    public JobsAdapter(Context context, List<Job> jobs,HomeViewModel homeViewModel) {
        this.context = context;
        this.jobs = jobs;
        this.homeViewModel = homeViewModel;
    }

    @NonNull
    @Override
    public JobsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobsAdapter.ViewHolder holder, int position) {
        Job job = jobs.get(position);

        holder.jobTitle.setText(job.getTitle());
        holder.jobDescription.setText(job.getDescription());
        holder.id = job.getId();

        if(job.getProfession() != 0){
            long profession_id = job.getProfession();
            Profession ourProfession = professionsBox.get(profession_id);
            holder.professionChip.setText(ourProfession.getName());
        }
        else{
            holder.professionChip.setText("Fit for all");
        }

    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Chip professionChip;
        TextView jobTitle, jobDescription;
        Button btnDetails;
        long id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            professionChip = itemView.findViewById(R.id.id_profession);
            jobTitle = itemView.findViewById(R.id.id_job_title);
            jobDescription = itemView.findViewById(R.id.id_job_description);
            btnDetails = itemView.findViewById(R.id.btn_see_details);


            btnDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    homeViewModel.setSelected(id);
                    Bundle bundle = new Bundle();
                    bundle.putLong("ID", id);
                    Navigation.findNavController(view).navigate(R.id.action_job_details, bundle);
                }
            });

        }
    }
}
