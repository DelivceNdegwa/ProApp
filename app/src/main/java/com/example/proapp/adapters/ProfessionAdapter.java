package com.example.proapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proapp.R;
import com.example.proapp.models.Profession;

import java.util.List;

public class ProfessionAdapter extends RecyclerView.Adapter<ProfessionAdapter.ViewHolder>{
    Context context;
    List<Profession> professions;

    public ProfessionAdapter(Context context, List<Profession> professions) {
        this.context = context;
        this.professions = professions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Where the itemView layout is called/created
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent,false);

        return new ProfessionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Where the itemView items are assigned data
        Profession profession = professions.get(position);
        holder.name.setText(profession.getName());
        Glide.with(context).load(profession.getIcon()).into(holder.icon);

    }

    @Override
    public int getItemCount() {
        return professions.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Where itemView items are called
            name = itemView.findViewById(R.id.txt_category);
            icon = itemView.findViewById(R.id.img_icon);


        }
    }
}
