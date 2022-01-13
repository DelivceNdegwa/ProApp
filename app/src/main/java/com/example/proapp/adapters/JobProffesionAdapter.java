package com.example.proapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proapp.R;
import com.example.proapp.models.Profession;
import com.google.android.material.chip.Chip;

import java.util.List;

public class JobProffesionAdapter extends RecyclerView.Adapter<JobProffesionAdapter.ViewHolder> {
    Context context;
    List<Profession> professions;
    public long profession_id;


    public JobProffesionAdapter(Context context, List<Profession> professions) {
        this.context = context;
        this.professions = professions;
    }

    @NonNull
    @Override
    public JobProffesionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profession, parent, false);

        return new JobProffesionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobProffesionAdapter.ViewHolder holder, int position) {
        Profession profession = professions.get(position);
        holder.professionName.setText(profession.getName());
        holder.id = profession.getId();
        holder.pro = profession.getName();
    }

    @Override
    public int getItemCount() { return professions.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Chip professionName;
        long id;
        String pro;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            professionName = itemView.findViewById(R.id.chip_profession);
            professionName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    profession_id = id;
                    Toast.makeText(context, "You have selected "+pro,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
